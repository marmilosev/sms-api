package com.example.userservice.controller.dto

import lombok.Getter
import lombok.Setter


@Getter
@Setter
class ApiResponse {
    var code: String? = null
    var message: String? = null
    var docsURL: String? = null

    constructor()
    constructor(code: String?, message: String?, docsURL: String?) {
        this.code = code
        this.message = message
        this.docsURL = docsURL
    }
}
