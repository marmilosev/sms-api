package com.infobip.sendMessageService;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SendMessageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendMessageServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}

}
