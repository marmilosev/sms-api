package com.example.sendmessageservice.service

import com.example.sendmessageservice.model.Message
import com.example.sendmessageservice.repository.MessageRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class MessageServiceImpl (private val messageRepository: MessageRepository) : MessageService {
    override fun saveMessage(message: Message): Message = messageRepository.save(message)

    override fun getAllMessages(): List<Message> = messageRepository.findAll()

    override fun getMessageById(id: Long): Message = messageRepository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    override fun deleteMessage(id: Long) {
        return if (messageRepository.existsById(id))
            messageRepository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    override fun updateMessage(id: Long, message: Message): Message {
        return if(messageRepository.existsById(id)){
            message.idMessage = id
            messageRepository.save(message)
        }else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

}