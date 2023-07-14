package com.example.apigateway.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
class ApiGatewayConfig {
    @Autowired
    var filter: AuthenticationFilter? = null

    @Bean
    fun routes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route(
                "UserServiceApplication"
            ) { r: PredicateSpec ->
                r.path("/v1/users/**")
                    .filters { f: GatewayFilterSpec ->
                        f.filter(
                            filter
                        )
                    }
                    .uri("lb://user-service")
            }
            .route(
                "SendmessageApplication"
            ) { r: PredicateSpec ->
                r.path("/v1/sms/sendSMS/**")
                    .filters { f: GatewayFilterSpec ->
                        f.filter(
                            filter
                        )
                    }
                    .uri("lb://send-message-service")
            }
            .route(
                "AuthenticationServiceApplication"
            ) { r: PredicateSpec ->
                r.path("/api/auth/**")
                    .filters { f: GatewayFilterSpec ->
                        f.filter(
                            filter
                        )
                    }
                    .uri("lb://auth-service")
            }
            .build()
    }

    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.csrf().disable()
        return http.build()
    }
}
