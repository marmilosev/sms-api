package com.infobip.demo4.controller;


import com.infobip.demo4.controller.dto.MessageDto;
import com.infobip.demo4.model.Message;
import com.infobip.demo4.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/v1/messages")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/add")
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto){
        Message messageRequest = modelMapper.map(messageDto, Message.class);
        Message message = messageService.saveMessage(messageRequest);
        MessageDto messageResponse = modelMapper.map(message, MessageDto.class);
        return new ResponseEntity<MessageDto>(messageResponse, HttpStatus.CREATED);
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
