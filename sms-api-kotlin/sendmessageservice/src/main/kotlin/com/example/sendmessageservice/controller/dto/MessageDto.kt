package com.example.sendmessageservice.controller.dto

import java.util.*

data class MessageDto(
    var idMessage : Long? = null,
    var dateTime: Date? = null,
    var messageText: String? = null,
    var userId: Long? = null,
    var number: String? = null
)