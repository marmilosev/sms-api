package com.ffos.sendmessageservice.controller;

import com.ffos.sendmessageservice.controller.dto.ApiResponse;
import com.ffos.sendmessageservice.controller.dto.MessageDto;
import com.ffos.sendmessageservice.controller.dto.UserDto;
import com.ffos.sendmessageservice.model.Message;
import com.ffos.sendmessageservice.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/v1/messages")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private ModelMapper modelMapper;
    private ApiResponse apiResponse;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createMessage(@RequestBody MessageDto messageDto){
        apiResponse = new ApiResponse();
        Message messageRequest = modelMapper.map(messageDto, Message.class);
        Message message = messageService.saveMessage(messageRequest);
        MessageDto messageResponse = modelMapper.map(message, MessageDto.class);
        apiResponse.setCode("5");
        apiResponse.setMessage("Message created successfully.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/messages/v1/4");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping
    public List<MessageDto> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        List<MessageDto> messageDtos = new ArrayList<>();

        for (Message message : messages) {
            UserDto userDto = webClientBuilder.build()
                    .get()
                    .uri("http://user-management-service/users/{id}", message.getUser())
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block();

            MessageDto messageDto = modelMapper.map(message, MessageDto.class);
            messageDto.setUser(userDto);
            messageDtos.add(messageDto);
        }

        return messageDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable("id") int id) {
        Message message = messageService.getMessageById(id);

        UserDto userDto = webClientBuilder.build()
                .get()
                .uri("http://user-management-service/users/{id}", message.getUser())
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();

        MessageDto messageDto = modelMapper.map(message, MessageDto.class);
        messageDto.setUser(userDto);

        return ResponseEntity.ok().body(messageDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateMessage(@PathVariable("id") int id, @RequestBody MessageDto messageDto) {
        Message messageRequest = modelMapper.map(messageDto, Message.class);
        messageRequest.setIdMessage(id);
//        user = userService.getUserById(messageDto.getUserId());
//        messageRequest.setUser(user);
        Message message = messageService.updateMessage(messageRequest);
        MessageDto messageResponse = modelMapper.map(message, MessageDto.class);
        apiResponse = new ApiResponse();
        apiResponse.setCode("6");
        apiResponse.setMessage("Message updated successfully.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/messages/v1/6");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMessage(@PathVariable("id") long id){
        apiResponse = new ApiResponse();
        messageService.deleteMessage(id);
        apiResponse.setCode("7");
        apiResponse.setMessage("Message with id " + id + " has been deleted successfully.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/messages/v1/7");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}

