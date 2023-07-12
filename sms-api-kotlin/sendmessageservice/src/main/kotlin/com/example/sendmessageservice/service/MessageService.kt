package com.example.sendmessageservice.service

import com.example.sendmessageservice.model.Message

interface MessageService {

    fun saveMessage(message: Message): Message
    fun getAllMessages(): List<Message>
    fun getMessageById(id: Long): Message
    fun deleteMessage(id: Long)
    fun updateMessage(id: Long, message: Message): Message
}