package com.infobip.springGatewaySecurity.service.proxy;

import com.infobip.springGatewaySecurity.model.ConnValidationResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


//@FeignClient(name = "authentication-service")
public interface AuthenticationServiceProxy {

	@GetMapping(value = "/api/v1/validateConnection", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ConnValidationResponse> validateConnection(@RequestHeader() String authToken);
//
//	@PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE},
//			produces = {MediaType.APPLICATION_JSON_VALUE})
//	public ResponseEntity<EntityModel<SalesGroupResponseModel>> createGroup(@Validated(ValidationLevel.onCreate.class) @RequestBody SalesGroupRequestModel salesGpRequest) ;
}
