package com.example.userservice

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class UserserviceApplication

fun main(args: Array<String>) {
	runApplication<UserserviceApplication>(*args)
}


@Bean
fun modelMapper(): ModelMapper? {
	return ModelMapper()
}