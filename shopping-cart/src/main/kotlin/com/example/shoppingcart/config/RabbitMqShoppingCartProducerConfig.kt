package com.example.shoppingcart.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

@Configuration
@PropertySource("classpath:rabbitmq-connection.properties")
data class RabbitMqShoppingCartProducerConfig(
    @Value("\${rabbitmq.host}") val host: String,
    @Value("\${rabbitmq.queue}") val queue: String,
    @Value("\${rabbitmq.exchange}") val exchange: String) {
    final val charset: Charset = StandardCharsets.UTF_8
}
