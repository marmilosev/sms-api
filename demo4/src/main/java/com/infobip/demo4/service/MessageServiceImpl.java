package com.infobip.demo4.service;

import com.infobip.demo4.model.Message;
import com.infobip.demo4.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return messageRepository.findById(id).get();
    }

    @Override
    public String deleteMessage(int id) {
        messageRepository.deleteById(id);
        return "Message with id " + id + " has been deleted.";
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

    @Override
    public Message findByUser(int id) {
        return messageRepository.findByUser(id);
    }
}
