//package com.example.userservice.controller
//
//import com.example.userservice.controller.dto.ApiResponse
//import com.example.userservice.controller.dto.UserDto
//import com.example.userservice.model.User
//import com.example.userservice.service.UserService
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.*
//import java.util.stream.Collectors
//
//
//@RequestMapping("/v1/users")
//@RestController
//class UserController {
//    @Autowired
//    private val userService: UserService? = null
//
//    @Autowired
//    private val modelMapper: ModelMapper? = null
//    var apiResponse: ApiResponse? = null
//    @PostMapping("/add")
//    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<ApiResponse> {
//        val userRequest: User = modelMapper.map(userDto, User::class.java)
//        val user: User = userService.saveUser(userRequest)
//        val userResponse: UserDto = modelMapper.map(user, UserDto::class.java)
//        apiResponse = ApiResponse()
//        apiResponse!!.code("8")
//        apiResponse.setMessage("User with username " + userDto.getUsername() + " successfully created.")
//        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/8")
//        return ResponseEntity.status(HttpStatus.OK).body<ApiResponse>(apiResponse)
//    }
//
//    @get:GetMapping
//    val allUsers: List<Any>
//        get() = userService.getAllUsers().stream().map { user -> modelMapper.map(user, UserDto::class.java) }
//            .collect(Collectors.toList())
//
//    @GetMapping("/{id}")
//    fun getUserById(@PathVariable("id") id: Int): ResponseEntity<UserDto> {
//        val user: User = userService.getUserById(id)
//        val userResponse: UserDto = modelMapper.map(user, UserDto::class.java)
//        return ResponseEntity.ok().body<UserDto>(userResponse)
//    }
//
//    @PutMapping("/{id}")
//    fun updateUser(@PathVariable("id") id: Int, @RequestBody @Valid userDto: UserDto): ResponseEntity<ApiResponse> {
//        userDto.setIdUser(id)
//        val userRequest: User = modelMapper.map(userDto, User::class.java)
//        val user: User = userService.updateUser(userRequest)
//        //        return ResponseEntity.ok().body(userResponse);
//        apiResponse = ApiResponse()
//        userDto.setIdUser(id)
//        userService.updateUser(user)
//        val userResponse: UserDto = modelMapper.map(user, UserDto::class.java)
//        apiResponse.setCode("9")
//        apiResponse.setMessage("User with username " + userDto.getUsername() + " successfully updated.")
//        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/9")
//        return ResponseEntity.status(HttpStatus.OK).body<ApiResponse>(apiResponse)
//    }
//
//    @DeleteMapping("/{id}")
//    fun deleteUser(@PathVariable id: Int): ResponseEntity<ApiResponse> {
//        userService.deleteUser(id)
//        apiResponse = ApiResponse()
//        apiResponse.setCode("11")
//        apiResponse.setMessage("User successfully deleted.")
//        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/11")
//        return ResponseEntity.status(HttpStatus.OK).body<ApiResponse>(apiResponse)
//    }
//}
