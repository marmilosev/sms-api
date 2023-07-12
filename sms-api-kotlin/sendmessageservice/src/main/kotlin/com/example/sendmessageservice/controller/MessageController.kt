package com.example.sendmessageservice.controller

import com.example.sendmessageservice.controller.dto.ApiResponse
import com.example.sendmessageservice.controller.dto.MessageDto
import com.example.sendmessageservice.controller.dto.UserDto
import com.example.sendmessageservice.model.Message
import com.example.sendmessageservice.model.toMessageDto
import com.example.sendmessageservice.service.MessageServiceImpl
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import java.util.stream.Collectors

@RestController
@RequestMapping("v1/messages")
class MessageController(val messageServiceImpl: MessageServiceImpl) {

    private var apiResponse: ApiResponse? = null
    var modelMapper : ModelMapper = ModelMapper()

    @GetMapping
    fun getAllMessages(): List<MessageDto?>? {
        return messageServiceImpl.getAllMessages().stream()
            .map {it.toMessageDto()}
            .collect(Collectors.toList())
    }
    @GetMapping("/{id}")
    fun getMessage(@PathVariable id: Long): ResponseEntity<MessageDto> {
        val message = messageServiceImpl.getMessageById(id)
        val messageRequest: MessageDto = modelMapper.map(message, MessageDto::class.java)
        return ResponseEntity.ok().body(messageRequest)
    }
//    @PostMapping("/add")
//    fun createMessage(@RequestBody messageDto: MessageDto?): ResponseEntity<ApiResponse?>? {
//        val messageRequest: Message = modelMapper.map(messageDto, Message::class.java)
//        val createdMessage: Message = messageServiceImpl.saveMessage(messageRequest)
//        val message = modelMapper.map(createdMessage, MessageDto::class.java)
//        apiResponse = ApiResponse(
//            "5",
//            "Message created successfully.",
//            "https://mmilosevic-diplomski-api.com/messages/v1/4"
//        )
//        return ResponseEntity.status(HttpStatus.OK).body(apiResponse)
//    }
        @PostMapping("/add")
        fun createMessage(@RequestBody messageDto: MessageDto?): ResponseEntity<ApiResponse?>? {
            val apiResponse = ApiResponse()
            // Fetch user details from user service based on user ID
            val userDto: UserDto? = WebClient.builder()
                .baseUrl("http://localhost:8081/v1/users")
                .build()
                .get()
                .uri("/{id}", messageDto?.userId)
                .retrieve()
                .bodyToMono(UserDto::class.java)
                .block()
            // Set the user details in the message
            messageDto?.userId = userDto?.idUser
            val messageRequest = modelMapper.map(
                messageDto,
                Message::class.java
            )
            val message: Message = messageServiceImpl.saveMessage(messageRequest)

            apiResponse.code = "5"
            apiResponse.message = "Message created successfully."
            apiResponse.docsURL = "https://mmilosevic-diplomski-api.com/messages/v1/${message.idMessage}"
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse)
}

    @DeleteMapping("/{id}")
    fun deleteMessage(@PathVariable id: Long) : ResponseEntity<ApiResponse> {
        messageServiceImpl.deleteMessage(id)
        apiResponse = ApiResponse(
            "7",
            "Message with id $id has been deleted successfully.",
            "https://mmilosevic-diplomski-api.com/messages/v1/7"
        )
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse)
    }
    @PutMapping("/{id}")
    fun updateMessage(@PathVariable id: Long, @RequestBody messageDto: MessageDto): ResponseEntity<ApiResponse> {
        val messageRequest = modelMapper.map(messageDto, Message::class.java)
        messageRequest.idMessage = id
        val message: Message = messageServiceImpl.updateMessage(id,messageRequest)
        val messageResponse = modelMapper.map(message, MessageDto::class.java)
        apiResponse = ApiResponse(
            "6",
            "Message with id $id has been updated successfully.",
            "https://mmilosevic-diplomski-api.com/messages/v1/6"
        )
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse)

    }
}