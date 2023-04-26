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

    private MessageDto messageDto;

    private MessageDto convertToDto(Message message){
        messageDto = new MessageDto();
        MessageDto messageDto = modelMapper.map(message, MessageDto.class);
        messageDto.setSubmissionDate();
    }

    private Message convertToEntity(MessageDto messageDto) throws ParseException {
        Message message = modelMapper.map(messageDto, Message.class);
        message.setSubmissionDate()
    }
//    @PostMapping("/add")
//    public ResponseEntity<ApiResponse> createMessage(@RequestBody @Valid Message message){
//        apiResponse = new ApiResponse();
//        messageService.saveMessage(message);
//        apiResponse.setCode("1");
//        apiResponse.setMessage("Message with id " + message.getIdMessage() + " successfully created.");
//        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/messages/v1/1");
//        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
//    }
    @PostMapping("/add")
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto){
//        Message messageRequest = modelMapper.map(messageDto, Message.class);
//        Message message = messageService.saveMessage(messageRequest);
//        MessageDto messageResponse = modelMapper.map(message, MessageDto.class);
//        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
        Message message = convertToEntity(messageDto);
        return convertToDto(messageService.saveMessage(message));
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
    public ResponseEntity<MessageDto> updateMessage(@PathVariable("id") int id, @RequestBody MessageDto messageDto){
        Message messageRequest = modelMapper.map(messageDto, Message.class);
        messageRequest.setIdMessage(id);
        Message message = messageService.updateMessage(messageRequest);
        MessageDto messageResponse = modelMapper.map(message, MessageDto.class);
        return ResponseEntity.ok().body(messageResponse);
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable("id") int id){
        return messageService.deleteMessage(id);
    }


}
