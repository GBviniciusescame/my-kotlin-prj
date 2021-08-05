package com.example.webshop.config

import org.springframework.context.annotation.Configuration

@Configuration
data class KafkaProducerConfig (
    val topic: String = "to.shopping.cart",
    val host: String = "localhost:9092")