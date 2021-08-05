package com.example.shoppingcart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class ShoppingCartApplication

fun main(args: Array<String>) {
	runApplication<ShoppingCartApplication>(*args)
}
