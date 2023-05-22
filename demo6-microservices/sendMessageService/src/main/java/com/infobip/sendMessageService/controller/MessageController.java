package com.infobip.sendMessageService.controller;

import com.infobip.sendMessageService.controller.dto.ApiResponse;
import com.infobip.sendMessageService.controller.dto.MessageDto;
import com.infobip.sendMessageService.controller.dto.ResponseDto;
import com.infobip.sendMessageService.controller.dto.UserDto;
import com.infobip.sendMessageService.model.Message;
import com.infobip.sendMessageService.model.User;
import com.infobip.sendMessageService.service.MessageService;
import com.infobip.sendMessageService.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestMapping("/v1/messages")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;
    private ApiResponse apiResponse;
    private WebClient webClient;
    private Message message;
    private User user;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createMessage(@RequestBody @Valid MessageDto messageDto){
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
        return messageService.getAllMessages().stream().map(message -> modelMapper.map(message, MessageDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable("id") int id) {
//        ResponseDto responseDto = new ResponseDto();
//
//        UserDto userDto = mapToUser(user);
//        MessageDto messageDto = webClient.get()
//                .uri("http://localhost:8081/v1/users/" + message.getUserId())
//                .retrieve().bodyToMono(MessageDto.class)
//                .block();
////        Message message = messageService.getMessageById(id);
////        MessageDto messageResponse = modelMapper.map(message, MessageDto.class);
////        return ResponseEntity.ok().body(messageResponse);
//        responseDto.setUser(userDto);
//        responseDto.setMessage(messageDto);
//        return ResponseEntity.ok().body(responseDto);
        Message message = messageService.getMessageById(id);
        MessageDto messageResponse = modelMapper.map(message, MessageDto.class);
        return ResponseEntity.ok().body(messageResponse);
    }

    private UserDto mapToUser(User user){
        UserDto userDto = new UserDto();
        userDto.setIdUser(user.getIdUser());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setNumber(user.getNumber());
        userDto.setMail(user.getMail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }


    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> updateMessage(@PathVariable("id") int id, @RequestBody @Valid MessageDto messageDto) {
        Message messageRequest = modelMapper.map(messageDto, Message.class);
        messageRequest.setIdMessage(id);
        user = userService.getUserById(messageDto.getUserId());
        messageRequest.setUser(user);
        Message message = messageService.updateMessage(messageRequest);
        MessageDto messageResponse = modelMapper.map(message, MessageDto.class);
        return ResponseEntity.ok().body(messageResponse);

//        if(!Objects.equals(id, messageDto.getIdMessage())){
//            throw new IllegalArgumentException("IDs don't match");
//        }
//        message = convertToEntity(messageDto);
//        messageService.updateMessage(message);
//        return ResponseEntity.ok().body(messageDto);

    }

    private Message convertToEntity(MessageDto messageDto) {
        message = modelMapper.map(messageDto, Message.class);
        if(messageDto.getIdMessage() != 0){
            Message oldMessage = messageService.getMessageById(messageDto.getIdMessage());
            message.setMessageText(oldMessage.getMessageText());
            message.setDateTime(oldMessage.getDateTime());
            message.setNumber(oldMessage.getNumber());
            message.setUser(oldMessage.getUser());
        }
        return message;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMessage(@PathVariable("id") int id){
        apiResponse = new ApiResponse();
        messageService.deleteMessage(id);
        apiResponse.setCode("7");
        apiResponse.setMessage("Message with id " + id + " has been deleted successfully.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/messages/v1/7");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}

