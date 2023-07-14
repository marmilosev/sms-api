package com.example.sendmessageservice.controller;


import com.example.sendmessageservice.controller.dto.ApiResponse;
import com.example.sendmessageservice.controller.dto.AuthDto;
import com.example.sendmessageservice.controller.dto.SmsRequest;
import com.example.sendmessageservice.controller.dto.UserDto;
import com.example.sendmessageservice.model.Message;
import com.example.sendmessageservice.model.User;
import com.example.sendmessageservice.service.MessageServiceImpl;
import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.ApiKey;
import com.infobip.BaseUrl;
import com.infobip.api.SmsApi;
import com.infobip.model.SmsAdvancedTextualRequest;
import com.infobip.model.SmsDestination;
import com.infobip.model.SmsResponse;
import com.infobip.model.SmsTextualMessage;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    private User user;
    private UserDto userDto;
    private Message message;
//    @Autowired
//    private UserService userService;
    @Autowired
    private MessageServiceImpl messageService;
    @Autowired
    private ModelMapper modelMapper;
    private ApiResponse apiResponse;
    public SmsController(){
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
                    "http://localhost:8080/api/auth/login",
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
        userDto = new UserDto();
        userDto.setUsername(smsRequest.getUsername());
        userDto.setPassword(smsRequest.getPassword());
        User userRequest = modelMapper.map(userDto, User.class);
//        User user = userService.saveUser(userRequest);
//        UserDto userResponse = modelMapper.map(user, UserDto.class);

        // Populate userDto with user information
//        userDto.setFirstName(user.getFirstName());
//        userDto.setLastName(user.getLastName());


        SmsTextualMessage smsMessage = new SmsTextualMessage()
//                .from(userResponse.getFirstName() + " " + userResponse.getLastName())
                .from("Marija")
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

