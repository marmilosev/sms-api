package com.example.sendmessageservice.controller.dto

import com.infobip.model.SmsResponseDetails

class ApiResponse (
    var code : String? = "",
    var message : String? = "",
    var docsURL : String? = "",
    var smsResponseDetails: List<SmsResponseDetails?>? = null
)