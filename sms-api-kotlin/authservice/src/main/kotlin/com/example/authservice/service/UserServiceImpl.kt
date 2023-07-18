package com.example.authservice.service

import com.example.userservice.model.User
import com.example.userservice.repository.UserRepository
import de.mkammerer.argon2.Argon2
import de.mkammerer.argon2.Argon2Factory
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*


@Service
class UserServiceImpl (private val userRepository: UserRepository) : UserService {

    val argon2 : Argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i)

    override fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun getUserById(id: Long): User = userRepository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    override fun saveUser(user: User): User {
        var hashPassword = argon2.hash(2, 1024, 4, user.password)
        var userHashedPassword = User(
            idUser = user.idUser,
            firstName = user.firstName,
            lastName = user.lastName,
            username = user.username,
            mail = user.mail,
            password = hashPassword,
            number = user.number
        )
        return userRepository.save(userHashedPassword)
    }




    override fun deleteUser(id: Long) {
        if(userRepository.existsById(id)) userRepository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    override fun updateUser(id: Long, user: User): User {
        return if (userRepository.existsById(id)){
            var hashPassword = argon2.hash(2, 1024, 4, user.password)
            var userHashPasswordUpdate = User(
                idUser = id,
                firstName = user.firstName,
                lastName = user.lastName,
                username = user.username,
                mail = user.mail,
                password = hashPassword,
                number = user.number
            )
            userRepository.save(userHashPasswordUpdate)
        }else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    override fun findByUsername(username: String): User {
        return userRepository.findByUsername(username)
    }


}
