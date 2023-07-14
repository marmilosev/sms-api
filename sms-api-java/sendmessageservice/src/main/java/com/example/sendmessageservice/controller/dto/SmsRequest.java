package com.example.sendmessageservice.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Data
@Builder
@Getter
@Setter
public class SmsRequest {
    @NotBlank(message = "validation.usernameSmsMandatory")
    @NotNull(message = "validation.usernameSmsMandatory")
    private String username;
    @NotBlank(message = "validation.passwordSmsMandatory")
    @NotNull(message = "validation.passwordSmsMandatory")
    private String password;
    @NotBlank(message = "validation.toNumberSmsMandatory")
    @NotNull(message = "validation.toNumberSmsMandatory")
    private String toNumber;
    @NotBlank(message = "validation.messageTextSmsMandatory")
    @NotNull(message = "validation.messageTextSmsMandatory")
    private String messageText;

    public SmsRequest() {
    }

    public SmsRequest(String username, String password, String toNumber, String messageText) {
        this.username = username;
        this.password = password;
        this.toNumber = toNumber;
        this.messageText = messageText;
    }
}