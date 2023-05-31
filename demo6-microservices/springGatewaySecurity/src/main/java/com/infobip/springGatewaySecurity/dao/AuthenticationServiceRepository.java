package com.infobip.springGatewaySecurity.dao;

import com.infobip.springGatewaySecurity.model.ConnValidationResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class AuthenticationServiceRepository {

//	@GetMapping(value = "/api/v1/validateConnection", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ConnValidationResponse validateConnection(String authToken) throws Exception {

		try {

			WebClient client = WebClient.builder().baseUrl("lb://authenticationService").build();
			ConnValidationResponse response = client.get().uri("/api/v1/validateConnection")
					.header("header", authToken)
					.retrieve().toEntity(ConnValidationResponse.class).flux().blockFirst().getBody();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new Exception("Gateway Error");
	}

}
