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
//    ApiClient apiClient = ApiClient.forApiKey(ApiKey.from("754db11a7a437bf416ebe4b5c616beb7-afcd6769-7edd-4cbb-bd0c-b1f47409f550"))
//            .withBaseUrl(BaseUrl.from("https://5vlply.api.infobip.com"))
//            .build();

    private ApiClient apiClient;

    private User user;
    private Message message;
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MessageServiceImpl messageService;

    private ApiResponse apiResponse;

//    private String auth = "mmilosevic:WXMEnxwU8714*"; //parametri

    //    public static String generateAuthorization(String username, String password){
//        return username + ":" + password;
//    }
//    private String auth = generateAuthorization(this.user.getUsername(), this.user.getPassword());
//    private String encoded;
    public SmsController() throws IOException {
//        this.encoded = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

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
    public ResponseEntity<ApiResponse> sendSMS(@NotNull @RequestBody SmsRequest smsRequest) {
        apiResponse= new ApiResponse();
        user = userService.findByEmail(smsRequest.getMail());

        if(user== null){
            apiResponse.setCode(1);
            apiResponse.setMessage("There is no user with provided mail in database");
            apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/sms/v1/1");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);

        }

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        if(!argon2.verify(user.getPassword(),smsRequest.getPassword())){
            apiResponse.setCode(2);
            apiResponse.setMessage("There is no user with provided password in database");
            apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/sms/v1/2");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
        }

        if(smsRequest.getToNumber() == null || smsRequest.getToNumber().isBlank()) {
            apiResponse.setCode(3);
            apiResponse.setMessage("Please provide number to send message");
            apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/sms/v1/3");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }

        if(smsRequest.getMessageText() == null|| smsRequest.getMessageText().isBlank()){
            apiResponse.setCode(4);
            apiResponse.setMessage("Please provide message text");
            apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/sms/v1/4");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }

        //ovdje sam autoriziran


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

            apiResponse.setCode(5);
            apiResponse.setMessage("Poslano");
            // iz smsResponse u SmsResponse prebacit svojtva i
            apiResponse.setSmsResponseDetails(smsResponse.getMessages());
            apiResponse.setDocsURL("https://gdjevca.com/v1/5");

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse); //preraditi u JSON response
        } catch (ApiException apiException) {
            apiException.printStackTrace();
            apiResponse.setCode(5);
            apiResponse.setMessage(apiException.rawResponseBody());

            apiResponse.setDocsURL("https://gdjevca.com/v1/5");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

}



