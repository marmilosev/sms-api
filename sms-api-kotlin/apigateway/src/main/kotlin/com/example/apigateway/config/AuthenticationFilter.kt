package com.example.apigateway.config

import jakarta.validation.constraints.NotNull
import lombok.AllArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@AllArgsConstructor
@RefreshScope
@Component
@Configuration
@ConditionalOnProperty(value = ["gateway.expose-unsecured-urls"], havingValue = "true", matchIfMissing = false)
@Slf4j
class AuthenticationFilter : GatewayFilter {
    private val gatewayProperties: @NotNull ApiGatewayProperties? = null
    private val logger = LoggerFactory.getLogger(AuthenticationFilter::class.java)

    @Autowired
    var discoveryClient: DiscoveryClient? = null

    @Autowired
    var webClientBuilder: WebClient.Builder? = null
    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void>? {
        val request = exchange.request
        if (isSecured(request)) {
            if (isAuthMissing(request)) return onError(
                exchange,
                "Authorization header is missing in request.",
                HttpStatus.UNAUTHORIZED
            )
            val authHeader = getAuthHeader(request)
            return if (!authHeader.isEmpty()) {
                val jwtToken = authHeader.replace("Bearer ", "")
                validateToken(exchange, chain, jwtToken)
            } else {
                onError(exchange, "Invalid token provided", HttpStatus.UNAUTHORIZED)
            }
        }
        return chain.filter(exchange)
    }

    fun isSecured(request: ServerHttpRequest): Boolean {
        return gatewayProperties!!.unsecuredUrls
            .stream()
            .noneMatch { uri -> request.uri.path.contains(uri) }
    }

    private fun onError(exchange: ServerWebExchange, err: String, httpStatus: HttpStatus?): Mono<Void> {
        val response = exchange.response
        response.setStatusCode(httpStatus)
        return response.setComplete()
    }

    private fun validateToken(exchange: ServerWebExchange, chain: GatewayFilterChain, token: String): Mono<Void>? {
        return webClientBuilder
            ?.build()
            ?.post()
            ?.uri("http://auth-service/api/auth/validate")
            ?.bodyValue(token)
            ?.exchangeToMono { clientResponse: ClientResponse ->
                if (clientResponse.statusCode().isError) {
                    return@exchangeToMono onError(
                        exchange,
                        "",
                        HttpStatus.resolve(clientResponse.statusCode().value())
                    )
                } else {
                    return@exchangeToMono chain.filter(exchange)
                }
            }
    }

    private fun getAuthHeader(request: ServerHttpRequest): String {
        return request.headers.getOrEmpty("Authorization")[0]
    }

    private fun isAuthMissing(request: ServerHttpRequest): Boolean {
        return !request.headers.containsKey("Authorization")
    }
}
