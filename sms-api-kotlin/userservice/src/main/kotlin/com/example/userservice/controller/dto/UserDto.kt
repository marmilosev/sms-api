//package com.example.userservice.controller.dto
//
//import lombok.Builder
//import lombok.Data
//import lombok.Getter
//import lombok.Setter
//import org.springframework.stereotype.Component
//
//
//@Component
//@Data
//@Builder
//@Getter
//@Setter
//class UserDto {
//    private var idUser: Long = 0
//
//    @NotBlank(message = "validation.firstNameMandatory")
//    @NotNull(message = "validation.firstNameMandatory")
//    private var firstName: String? = null
//
//    @NotBlank(message = "validation.lastNameMandatory")
//    @NotNull(message = "validation.lastNameMandatory")
//    private var lastName: String? = null
//
//    @NotBlank(message = "validation.userNameMandatory")
//    @NotNull(message = "validation.userNameMandatory")
//    private var username: String? = null
//
//    @NotBlank(message = "validation.mailMandatory")
//    @NotNull(message = "validation.mailMandatory")
//    @Email(message = "validation.mailMandatoryAtSign")
//    private var mail: String? = null
//
//    @NotBlank(message = "validation.passwordMandatory")
//    @NotNull(message = "validation.passwordMandatory")
//    private var password: String? = null
//
//    @NotBlank(message = "validation.numberMandatory")
//    @NotNull(message = "validation.numberMandatory")
//    private var number: String? = null
//
//    constructor()
//    constructor(
//        idUser: Long,
//        firstName: String?,
//        lastName: String?,
//        username: String?,
//        mail: String?,
//        password: String?,
//        number: String?
//    ) {
//        this.idUser = idUser
//        this.firstName = firstName
//        this.lastName = lastName
//        this.username = username
//        this.mail = mail
//        this.password = password
//        this.number = number
//    }
//}