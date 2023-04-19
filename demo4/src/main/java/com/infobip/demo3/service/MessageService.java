package com.infobip.demo3.service;

import com.infobip.demo3.model.Message;

import java.util.List;

public interface MessageService {

    default Message saveMessage (Message message){
        Integer id = (int) Math.random();
        return saveMessage(message);
    }
    List<Message> getAllMessages();
    Message getMessageById(int id);
    String deleteMessage (int id);
    Message updateMessage(Message message);

    Message findByUser(int id);
}