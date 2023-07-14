package com.example.sendmessageservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class SendmessageserviceApplication

fun main(args: Array<String>) {
	runApplication<SendmessageserviceApplication>(*args)
}
