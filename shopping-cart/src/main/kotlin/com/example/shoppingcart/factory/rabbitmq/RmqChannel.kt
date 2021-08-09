package com.example.shoppingcart.factory.rabbitmq

import com.example.shoppingcart.config.RabbitMqShoppingCartProducerConfig
import com.example.shoppingcart.model.Product
import com.rabbitmq.client.Channel

class RmqChannel(
    private val queue: Channel,
    private val config: RabbitMqShoppingCartProducerConfig) {
    fun publish(product: Product) {
        queue.basicPublish(config.exchange,
            config.queue,
            null,
            product.uuid.toString().toByteArray(config.charset))
    }
}