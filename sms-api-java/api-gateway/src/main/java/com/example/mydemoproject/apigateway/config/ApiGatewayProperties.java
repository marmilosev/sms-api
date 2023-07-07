package com.example.mydemoproject.apigateway.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties("gateway")
@RefreshScope
@Slf4j
public class ApiGatewayProperties {

    private boolean exposeUnsecuredUrls = false;
    private List<String> unsecuredUrls = new ArrayList<>();

    public void logGatewayProperties() {
        log.info("***** Gateway Properties: {}", toString());
    }
}
