package com.example.webshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebshopApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<WebshopApplication>(*args)
		}
	}
}
