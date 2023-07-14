package com.example.userservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class UserserviceApplication

fun main(args: Array<String>) {
	runApplication<UserserviceApplication>(*args)
}
