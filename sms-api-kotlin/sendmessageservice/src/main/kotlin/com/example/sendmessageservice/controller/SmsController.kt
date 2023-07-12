package com.example.sendmessageservice.controller

import com.example.sendmessageservice.controller.dto.ApiResponse
import com.example.sendmessageservice.controller.dto.AuthDto
import com.example.sendmessageservice.controller.dto.SmsRequest
import com.example.sendmessageservice.controller.dto.UserDto
import com.example.sendmessageservice.model.Message
import com.example.sendmessageservice.service.MessageServiceImpl
import com.infobip.ApiClient
import com.infobip.ApiException
import com.infobip.ApiKey
import com.infobip.BaseUrl
import com.infobip.api.SmsApi
import com.infobip.model.SmsAdvancedTextualRequest
import com.infobip.model.SmsDestination
import com.infobip.model.SmsResponse
import com.infobip.model.SmsTextualMessage
import jakarta.annotation.PostConstruct
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.util.*

@RequestMapping("v1/sms")
@RestController
class SmsController {
    @Value("\${infobip.apiKey}")
    lateinit var apiKey: String

    @Value("\${infobip.baseUrl}")
    lateinit var baseUrl: String

    private var apiClient: ApiClient? = null
    private var userDto: UserDto? = null

    @Autowired
    private var messageServiceImpl: MessageServiceImpl? = null

    //    @Autowired
//    private UserService userService;
//    @Autowired
    private var apiResponse: ApiResponse? = null

    @PostConstruct
    fun init() {
        apiClient = ApiClient.forApiKey(ApiKey.from(apiKey))
            .withBaseUrl(BaseUrl.from(baseUrl))
            .build()
    }

    //Send SMS when execute
    @PostMapping("/sendSMS")
    @ResponseBody
    fun sendSMS(@RequestBody smsRequest: SmsRequest): ResponseEntity<ApiResponse> {
        apiResponse = ApiResponse()
        // user = userService.findByUsername(smsRequest.getUsername());
        var params = HashMap<String, String>()
        params["username"] = smsRequest.username
        params["password"] = smsRequest.password
//        AUTENTIKACIJA
        try {
            var response: ResponseEntity<AuthDto> = RestTemplate().postForEntity(
                "http://localhost:8081/v1/authenticate",
                params, AuthDto::class.java
            )
            System.out.println(response.body)
        } catch (ex: Exception) {
//            //  throw new InvalidUserIdException(
//            //        "User Id " + orderDetails.getUserId()
//            //                 + " Not Found");
            ex.printStackTrace()
        }
        var smsApi = SmsApi(apiClient)
        userDto = UserDto()
        var userDto = userDto as? UserDto
        userDto?.username = smsRequest.username
        userDto?.password = smsRequest.password

//        var userRequest = modelMapper!!.map(userDto, User())
////        var user: User = userService.saveUser(userRequest)
//        var userResponse = modelMapper!!.map(user, UserDto())


        // Populate userDto with user information
//        userDto.setFirstName(user.getFirstName());
//        userDto.setLastName(user.getLastName());

        var smsMessage = SmsTextualMessage()
            .from("Marija")
//            .from("${userResponse.firstName} ${userResponse.lastName}")
            .addDestinationsItem(SmsDestination().to(smsRequest.toNumber))
            .text(smsRequest.messageText)

        var messages: MutableList<SmsTextualMessage> = ArrayList()
        messages.add(smsMessage)
        var smsMessageRequest = SmsAdvancedTextualRequest().messages(messages)
        var smsResponse: SmsResponse? = null
        return try {
            smsResponse = smsApi.sendSmsMessage(smsMessageRequest).execute()
            var message = Message()
            // message.setUser(user);
            message.number = smsRequest.toNumber
            message.dateTime = Date()
            message.messageText = smsRequest.messageText
            messageServiceImpl?.saveMessage(message)
            apiResponse = ApiResponse(
                "5",
                "Message sent",
                "https://mmilosevic-diplomski-api.com/sms/v1/5",
                smsResponse.messages
            )
            //            apiResponse.setTimestamp((Timestamp) new Date());
            ResponseEntity.status(HttpStatus.OK).body<ApiResponse>(apiResponse)
        } catch (apiException: ApiException) {
            apiException.printStackTrace()
            apiResponse = ApiResponse(
                "6",
                apiException.rawResponseBody(),
                "https://mmilosevic-diplomski-api.com/sms/v1/6"
            )
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<ApiResponse>(apiResponse)
        }
    }
}