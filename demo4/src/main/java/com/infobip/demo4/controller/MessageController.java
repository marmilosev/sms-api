package com.infobip.demo4.controller;


import com.infobip.demo4.controller.dto.ApiResponse;
import com.infobip.demo4.controller.dto.MessageDto;
import com.infobip.demo4.model.Message;
import com.infobip.demo4.service.MessageService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
        Message message = messageService.getMessageById(id);
        MessageDto messageResponse = modelMapper.map(message, MessageDto.class);
        return ResponseEntity.ok().body(messageResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> updateMessage(@PathVariable("id") int id, @RequestBody @Valid MessageDto messageDto){
        Message messageRequest = modelMapper.map(messageDto, Message.class);
        messageRequest.setIdMessage(id);
        Message message = messageService.updateMessage(messageRequest);
        MessageDto messageResponse = modelMapper.map(message, MessageDto.class);
        return ResponseEntity.ok().body(messageResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMessage(@PathVariable("id") int id){
        apiResponse = new ApiResponse();
        messageService.deleteMessage(id);
        apiResponse.setCode("7");
        apiResponse.setMessage("User with id " + id + " has been deleted successfully.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/messages/v1/7");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}
