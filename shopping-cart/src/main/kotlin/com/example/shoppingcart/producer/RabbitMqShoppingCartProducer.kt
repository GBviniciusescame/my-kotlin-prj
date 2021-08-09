package com.example.shoppingcart.producer

import com.example.shoppingcart.factory.rabbitmq.RmqChannel
import com.example.shoppingcart.factory.rabbitmq.RmqChannelFactory
import com.example.shoppingcart.model.Product
import org.springframework.stereotype.Service

@Service
class RabbitMqShoppingCartProducer(channel: RmqChannelFactory) {
    private val rmqChannel: RmqChannel
    init {
        rmqChannel = channel.createChannel()
    }

    fun create(product: Product) {
        rmqChannel.publish(product)
    }
}
