package com.example.sendmessageservice.controller.dto

import org.springframework.stereotype.Component

class SmsRequest(

    var username: String,
    var password: String,
    var toNumber: String,
    var messageText: String
)