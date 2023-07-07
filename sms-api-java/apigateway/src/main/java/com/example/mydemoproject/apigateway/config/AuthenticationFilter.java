package com.example.mydemoproject.apigateway.config;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.client.Client;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RefreshScope
@Component
@Configuration
@ConditionalOnProperty(value = "gateway.expose-unsecured-urls", havingValue = "true", matchIfMissing = false)
@Slf4j
public class AuthenticationFilter implements GatewayFilter {

    private final @NotNull ApiGatewayProperties gatewayProperties;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    WebClient.Builder webClientBuilder;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (isSecured(request)) {
            if (this.isAuthMissing(request))
                return this.onError(exchange, "Authorization header is missing in request.", HttpStatus.UNAUTHORIZED);

            String authHeader = getAuthHeader(request);
            if (!authHeader.isEmpty()) {
                String jwtToken = authHeader.replace("Bearer ", "");
                return validateToken(exchange, chain, jwtToken);
            } else {
                return this.onError(exchange, "Invalid token provided", HttpStatus.UNAUTHORIZED);
            }

        }

        return chain.filter(exchange);
    }

    public boolean isSecured(ServerHttpRequest request) {
        return gatewayProperties.getUnsecuredUrls()
                .stream()
                .noneMatch(uri -> request.getURI().getPath().contains(uri));
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private Mono<Void> validateToken(ServerWebExchange exchange, GatewayFilterChain chain, String token) {
        return webClientBuilder
                .build()
                .post()
                .uri("http://auth-service/api/auth/validate")
                .bodyValue(token)
                .exchangeToMono(
                        clientResponse -> {
                            if(clientResponse.statusCode().isError()) {
                                return onError(exchange, "", HttpStatus.resolve(clientResponse.statusCode().value()));
                            } else {
                                return chain.filter(exchange);
                            }
                        }
                );
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }
}
