package com.infobip.demo4.controller;

import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.ApiKey;
import com.infobip.BaseUrl;
import com.infobip.api.SmsApi;
import com.infobip.demo4.controller.dto.ApiResponse;
import com.infobip.demo4.controller.dto.SmsRequest;
import com.infobip.demo4.model.Message;
import com.infobip.demo4.model.User;
import com.infobip.demo4.service.MessageServiceImpl;
import com.infobip.demo4.service.UserServiceImpl;
import com.infobip.model.SmsAdvancedTextualRequest;
import com.infobip.model.SmsDestination;
import com.infobip.model.SmsResponse;
import com.infobip.model.SmsTextualMessage;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/v1/sms")
@RestController
public class SmsController {

    @Value("${infobip.apiKey}")
    private String apiKey;
    @Value("${infobip.baseUrl}")
    private String baseUrl;

    private ApiClient apiClient;

    private User user;
    private Message message;
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MessageServiceImpl messageService;

    private ApiResponse apiResponse;

    public SmsController() throws IOException {
    }

    @PostConstruct
    public void init() {
        this.apiClient = ApiClient.forApiKey(ApiKey.from(apiKey))
            .withBaseUrl(BaseUrl.from(baseUrl))
            .build();
    }

    //Send SMS when execute
    @PostMapping("/sendSMS")
    @ResponseBody
    public ResponseEntity<ApiResponse> sendSMS(@NotNull @RequestBody @Valid SmsRequest smsRequest) {
        apiResponse= new ApiResponse();
        user = userService.findByUsername(smsRequest.getUsername());


        SmsApi smsApi = new SmsApi(apiClient);
        SmsTextualMessage smsMessage = new SmsTextualMessage()
                .from(user.getFirstName() + " " + user.getLastName())
                .addDestinationsItem(new SmsDestination().to(smsRequest.getToNumber()))
                .text(smsRequest.getMessageText());


        List<SmsTextualMessage> messages= new ArrayList<>();
        messages.add(smsMessage);

        SmsAdvancedTextualRequest smsMessageRequest = new SmsAdvancedTextualRequest()

                .messages(messages);
        SmsResponse smsResponse=null;
        try {
            smsResponse = smsApi.sendSmsMessage(smsMessageRequest).execute();
            Message message = new Message();
            message.setUser(user);
            message.setNumber(smsRequest.getToNumber());
            message.setDateTime(new Date());
            message.setMessageText(smsRequest.getMessageText());

            messageService.saveMessage(message);

            apiResponse.setCode("5");
            apiResponse.setMessage("Message sent");
            apiResponse.setSmsResponseDetails(smsResponse.getMessages());
            apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/sms/v1/5");

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (ApiException apiException) {
            apiException.printStackTrace();
            apiResponse.setCode("6");
            apiResponse.setMessage(apiException.rawResponseBody());
            apiResponse.setDocsURL("\"https://mmilosevic-diplomski-api.com/sms/v1/6");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

}



