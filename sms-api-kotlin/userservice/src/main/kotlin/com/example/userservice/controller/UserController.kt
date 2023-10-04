package com.example.userservice.controller

import com.example.userservice.controller.dto.ApiResponse
import com.example.userservice.controller.dto.UserDto
import com.example.userservice.model.User
import com.example.userservice.model.toUserDto
import com.example.userservice.service.UserServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/users")
class UserController (val userServiceImpl: UserServiceImpl){

    var apiResponse: ApiResponse? = null

    @GetMapping
    fun getAllUsers() : List<UserDto>{
        var users = userServiceImpl.getAllUsers()
        return users.map { it.toUserDto() }
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserDto>{
        var user = userServiceImpl.getUserById(id)
        var userDto = user.toUserDto()
        return ResponseEntity.ok().body(userDto)
    }

    @PostMapping("/add")
    fun saveUser(@RequestBody user: User) : ResponseEntity<ApiResponse> {
        var savedUser = userServiceImpl.saveUser(user)
        savedUser.toUserDto()
        apiResponse = ApiResponse(
            "8",
            "User with username ${savedUser.username} successfully created.",
            "https://oziz.ffos.hr/nastava20192020/mmilosevic_19/DiplomskiRad/HTML-API-Docs/users/v1/index.html"
        )
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse)
    }

    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
    fun deleteUser(@PathVariable id: Long) : ResponseEntity<ApiResponse>{
        userServiceImpl.deleteUser(id)
        apiResponse = ApiResponse(
            "11",
            "Deleted",
            "https://oziz.ffos.hr/nastava20192020/mmilosevic_19/DiplomskiRad/HTML-API-Docs/users/v1/index.html"
        )
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse)
    }

    @PutMapping("/{idUser}")
    fun updateUser(@PathVariable idUser: Long, @RequestBody user: User): ResponseEntity<ApiResponse> {
        var updatedUser = userServiceImpl.updateUser(idUser, user)
        updatedUser.toUserDto()
        apiResponse = ApiResponse(
            "9",
            "User with username ${updatedUser.username} successfully updated.",
            "https://oziz.ffos.hr/nastava20192020/mmilosevic_19/DiplomskiRad/HTML-API-Docs/users/v1/index.html"
        )
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse)
    }
}
