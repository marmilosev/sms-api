package com.infobip.userManagementService.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Builder
@Getter
@Setter
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    @NotBlank(message = "validation.usernameAuthMandatory")
    @NotNull(message = "validation.usernameAuthMandatory")
    private String username;
    @NotBlank(message = "validation.passwordAuthMandatory")
    @NotNull(message = "validation.passwordAuthMandatory")
    private String password;
    //need default constructor for JSON Parsing
    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

