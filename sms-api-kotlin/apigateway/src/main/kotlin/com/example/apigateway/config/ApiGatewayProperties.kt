package com.example.apigateway.config

import lombok.Getter
import lombok.Setter
import lombok.ToString
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.context.annotation.Configuration


@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties("gateway")
@RefreshScope
@Slf4j
class ApiGatewayProperties {
    val exposeUnsecuredUrls = false
    val unsecuredUrls: List<String> = ArrayList()

    private val log: Logger = LoggerFactory.getLogger(ApiGatewayProperties::class.java)

    fun logGatewayProperties() {
        ApiGatewayProperties.log.info("***** Gateway Properties: {}", toString())
    }
}
