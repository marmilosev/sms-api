package com.example.userservice

import lombok.extern.slf4j.Slf4j
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class UserserviceApplication

fun main(args: Array<String>) {
	runApplication<UserserviceApplication>(*args)
	// functional programming
	val numbers = listOf(1, 2, 3)
	val numbersDoubled = numbers.map { it * 2 }
	println(numbersDoubled)

	//smart cast
	val stringText: String? = "Hello world!"
	val nullableText: String? = null

	printStringLength(stringText)
	printStringLength(nullableText)

	// ternary operator doesn't exist
	val x = 2

	val result = if (x + 2 == 4) "yes" else "no"
	println("X equals 2 - $result")

	//if - when
	val dayOfWeek = 3

	val dayName = when (dayOfWeek) {
		1 -> "Monday"
		2 -> "Tuesday"
		3 -> "Wednesday"
		4 -> "Thursday"
		5 -> "Friday"
		else -> "Weekend"
	}

	println("It's $dayName")
}

fun printStringLength(str: String?) {
	if (str != null) {
		// Smart cast: Compiler knows that str is not null inside this block
		println("String length: ${str.length}")
	} else {
		println("String is null")
	}
}