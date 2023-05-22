package com.infobip.sendMessageService.controller;


import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.ApiKey;
import com.infobip.BaseUrl;
import com.infobip.api.SmsApi;
import com.infobip.model.SmsAdvancedTextualRequest;
import com.infobip.model.SmsDestination;
import com.infobip.model.SmsResponse;
import com.infobip.model.SmsTextualMessage;
import com.infobip.sendMessageService.controller.dto.ApiResponse;
import com.infobip.sendMessageService.controller.dto.AuthDto;
import com.infobip.sendMessageService.controller.dto.SmsRequest;
import com.infobip.sendMessageService.controller.dto.UserDto;
import com.infobip.sendMessageService.model.Message;
import com.infobip.sendMessageService.service.MessageServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/v1/sms")
@RestController
public class SmsController {

    @Value("${infobip.apiKey}")
    private String apiKey;
    @Value("${infobip.baseUrl}")
    private String baseUrl;
    private ApiClient apiClient;
    private UserDto user;
    private Message message;
    //@Autowired
    //private UserServiceImpl userService;
    @Autowired
    private MessageServiceImpl messageService;
    private ApiResponse apiResponse;
//    private final SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//    private final SimpleDateFormat  date_time_format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

//    private java.sql.Date parseDate(String date) {
//        try {
//            return (java.sql.Date) new Date(date_format.parse(date).getTime());
//        } catch (ParseException e) {
//            throw new IllegalArgumentException(e);
//        }
//    }
//    private Timestamp parseTimestamp(String timestamp) {
//        try {
//            return new Timestamp(date_time_format.parse(timestamp).getTime());
//        } catch (ParseException e) {
//            throw new IllegalArgumentException(e);
//        }
//    }
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
       // user = userService.findByUsername(smsRequest.getUsername());

        HashMap<String, String> params = new HashMap<>();
        params.put("username", smsRequest.getUsername());
        params.put("password", smsRequest.getPassword());
        try {
            ResponseEntity<AuthDto> response
                    = new RestTemplate().postForEntity(
                    "http://localhost:8081/v1/authenticate",
                     params, AuthDto.class);
            System.out.println(response.getBody().getToken());
        }
        catch (Exception ex) {
          //  throw new InvalidUserIdException(
            //        "User Id " + orderDetails.getUserId()
           //                 + " Not Found");
            ex.printStackTrace();
        }

        SmsApi smsApi = new SmsApi(apiClient);
        user = new UserDto();
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
           // message.setUser(user);
            message.setNumber(smsRequest.getToNumber());
            message.setDateTime(new Date());
            message.setMessageText(smsRequest.getMessageText());

            messageService.saveMessage(message);

            apiResponse.setCode("5");
            apiResponse.setMessage("Message sent");
            apiResponse.setSmsResponseDetails(smsResponse.getMessages());
            apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/sms/v1/5");
//            apiResponse.setTimestamp((Timestamp) new Date());
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

