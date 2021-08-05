package com.example.shoppingcart.config

import org.springframework.context.annotation.Configuration

@Configuration
data class KafkaConsumerConfig(
    val host: String = "localhost:9092",
    val groupId: String = "shopping-cart",
    val topics: List<String> = listOf("to.shopping.cart"))