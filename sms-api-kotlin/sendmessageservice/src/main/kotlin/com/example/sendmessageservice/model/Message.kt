package com.example.sendmessageservice.model

import com.example.sendmessageservice.controller.dto.MessageDto
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "messages")
class Message (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idMessage: Long? = null,
    var number: String? = null,
    var dateTime: Date? = null,
    var messageText: String? = null,
    //    @JoinColumn(name = "user_idUser")
    @ManyToOne
    var userId: User? = null
)

fun Message.toMessageDto() = MessageDto(
    idMessage = idMessage,
    number = number,
    dateTime = dateTime,
    messageText = messageText,
    userId= userId?.idUser
)