package com.ffos.sendmessageservice.service;

import com.ffos.sendmessageservice.model.Message;
import com.ffos.sendmessageservice.repository.MessageRepository;
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
    public Message getMessageById(long id) {
        Message message = messageRepository.findById(id).get();
//        MessageDto messageDto = new MessageDto(
//                message.getIdMessage(),
//                message.getUser().getIdUser(),
//                message.getNumber(),
//                message.getDateTime(),
//                message.getMessageText()
//        );
        return message;
    }


    @Override
    public void deleteMessage(long id) {
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
