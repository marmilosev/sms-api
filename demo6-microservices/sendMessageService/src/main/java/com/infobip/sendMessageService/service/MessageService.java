package com.infobip.sendMessageService.service;

import com.infobip.sendMessageService.controller.dto.ResponseDto;
import com.infobip.sendMessageService.model.Message;

import java.util.List;

public interface MessageService {


    Message saveMessage (Message message);
    List<Message> getAllMessages();
    Message getMessageById(int id);
    void deleteMessage (int id);
    Message updateMessage(Message message);
}
