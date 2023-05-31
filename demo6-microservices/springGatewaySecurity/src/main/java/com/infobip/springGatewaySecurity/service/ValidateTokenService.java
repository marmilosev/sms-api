package com.infobip.springGatewaySecurity.service;

import com.infobip.springGatewaySecurity.dao.AuthenticationServiceRepository;
import com.infobip.springGatewaySecurity.model.ConnValidationResponse;
import com.infobip.springGatewaySecurity.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateTokenService {

//    @Autowired
//    private AuthenticationServiceProxy authenticationServiceProxy;

    @Autowired
    private AuthenticationServiceRepository authenticationServiceRepository;


    public ConnValidationResponse validateAuthenticationToken(String bearerToken) throws Exception {

        if(Utilities.isTokenValid(bearerToken)) {
            String token = bearerToken.replace("Bearer", "");

            try {
//                ResponseEntity<ConnValidationResponse> responseEntity = authenticationServiceProxy.validateConnection(token);
//                if(responseEntity.getStatusCode().is2xxSuccessful()) {
//                    return responseEntity.getBody();
//                } else if(responseEntity.getStatusCode().is4xxClientError()) {
//                    int errorCode = responseEntity.getStatusCode().value();
//                    if(errorCode==401) {
//                        throw new BusinessException("", HttpStatus.UNAUTHORIZED, "Unauthorized");
//                    } else if(errorCode==403) {
//                        throw new BusinessException("", HttpStatus.FORBIDDEN, "Forbidden");
//                    }
//                }

                return authenticationServiceRepository.validateConnection(bearerToken);

            } catch (Exception e) {
                throw new Exception("Unauthorized");

            }
        }

        throw new Exception("Unauthorized");

    }

}
