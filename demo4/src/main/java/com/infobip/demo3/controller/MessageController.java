package com.infobip.demo3.controller;


import com.infobip.demo3.model.Message;
import com.infobip.demo3.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/messages")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/add")
    public Message createMessage(@RequestBody Message message){
//        if(message.getMessageText().isBlank()){
//            return "Pleasee fill in all requested information";
//        }else{
        return messageService.saveMessage(message);
//        }

    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("id") int id) {
        Message message = messageService.getMessageById(id);
        if (message != null) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable("id") int id, @RequestBody Message message){
//        if(message.getMessageText().isBlank()){
//            return "Please fill in all requested information";
//        }else{

        message.setIdMessage(id);
        return messageService.updateMessage(message);
//        }
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable("id") int id){
        return messageService.deleteMessage(id);
    }


}
