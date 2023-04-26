package com.infobip.demo4.service;

import com.infobip.demo4.controller.dto.MessageDto;
import com.infobip.demo4.model.Message;

import java.util.List;

public interface MessageService {


    Message saveMessage (Message message);
    List<Message> getAllMessages();
    Message getMessageById(int id);
    void deleteMessage (int id);
    Message updateMessage(Message message);
}