package com.infobip.demo4.service;

import com.infobip.demo4.controller.dto.MessageDto;
import com.infobip.demo4.model.Message;
import com.infobip.demo4.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    @Override
    public Message getMessageById(int id) {
        Optional<Message> result = Optional.of(messageRepository.findById(id).get());
        if(result.isPresent()){
            return result.get();
        }else{
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public void deleteMessage(int id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message updateMessage(Message message) {
        Optional<Message> existingMessageOptional = messageRepository.findById(message.getIdMessage());
        if (existingMessageOptional.isPresent()) {
            Message existingMessage = existingMessageOptional.get();
            existingMessage.setMessageText(message.getMessageText());
            existingMessage.setDateTime(message.getDateTime());
            existingMessage.setNumber(message.getNumber());
            existingMessage.setUser(message.getUser());
            return messageRepository.save(existingMessage);
        } else {
            throw new IllegalArgumentException("Invalid message ID");
        }
    }
}
