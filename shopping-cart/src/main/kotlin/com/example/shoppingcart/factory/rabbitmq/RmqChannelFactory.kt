package com.example.shoppingcart.factory.rabbitmq

import com.example.shoppingcart.config.RabbitMqShoppingCartProducerConfig
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class RmqChannelFactory(
    private val config: RabbitMqShoppingCartProducerConfig) {
    private val channel: Channel

    init {
        val conn: Connection = ConnectionFactory().newConnection(config.host)
        channel = conn.openChannel().orElseThrow {
                Exception("falha ao instanciar Channel!")
            }
    }

    fun createChannel() = RmqChannel(channel, config)

    private fun declareQueue() {
        if (channel.isOpen) {
            channel.use {
                    ch ->
                try {
                    ch.queueDeclare(config.queue, true,
                        false,
                        false,
                        null)
                } catch (e: IOException) {
                    println("queue (${config.queue}) already declared")
                }
            }
        }
    }
}