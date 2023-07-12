package com.example.sendmessageservice.controller.dto

import lombok.Data
import lombok.Getter
import lombok.Setter


@Data
@Getter
@Setter
class AuthDto {
    private var token: String? = null

    constructor()
    constructor(token: String?) {
        this.token = token
    }
}
