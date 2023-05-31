package com.infobip.springGatewaySecurity.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infobip.springGatewaySecurity.filter.AuthenticationPrefilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DefaultConfig {

    @Value("${spring.gateway.excludedURLsNew}")
    private String urlsStrings;

    @Bean
    @Qualifier("excludedUrls")
    public List<String> excludedUrls() {
        return Arrays.stream(urlsStrings.split(",")).collect(Collectors.toList());
    }

    @Bean
    public ObjectMapper objectMapper() {
        JsonFactory factory = new JsonFactory();
        factory.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

        ObjectMapper objectMapper = new ObjectMapper(factory);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        return objectMapper;
    }


    @Bean
    public RouteLocator routes(
            RouteLocatorBuilder builder,
            AuthenticationPrefilter authFilter) {
        return builder.routes()
                .route("auth-service-route", r -> r.path("/api/auth/**")
                        .filters(f ->
                                //f.rewritePath("/api/auth/(?<segment>.*)", "$\\{segment}")
                                f.rewritePath("/api/auth/(?<segment>.*)", "$\\{segment}")
                                        .filter(authFilter.apply(new AuthenticationPrefilter.Config())))
                        .uri("lb://authenticationService"))

                .route("user-service-route", r -> r.path("/api/users/**")
                .filters(f ->
                        f.filter(authFilter.apply(new AuthenticationPrefilter.Config()))
                                .stripPrefix(2))
                    .uri("lb://userManagementService"))
                .route("sendMessageService-route", r -> r.path("/api/messages/**")
                        .filters(f ->
                                f.filter(authFilter.apply(new AuthenticationPrefilter.Config()))
                                        .stripPrefix(2))
                        .uri("lb://sendMessageService"))
                .build();
    }
}
