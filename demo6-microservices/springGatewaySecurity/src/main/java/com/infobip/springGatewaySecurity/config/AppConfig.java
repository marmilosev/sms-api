package com.infobip.springGatewaySecurity.config;

import com.infobip.springGatewaySecurity.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public JwtUtil util(){
        return new JwtUtil();
    }

}
