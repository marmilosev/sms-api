package com.ffos.sendmessageservice.service;

import com.ffos.sendmessageservice.model.Message;

import java.util.List;

public interface MessageService {


    Message saveMessage (Message message);
    List<Message> getAllMessages();
    Message getMessageById(long id);
    void deleteMessage (long id);
    Message updateMessage(Message message);
}
