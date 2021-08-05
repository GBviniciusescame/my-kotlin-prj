package com.example.webshop.producer

import com.example.webshop.config.KafkaProducerConfig
import com.example.webshop.model.Product
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.kafka.clients.producer.*
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.streams.StreamsConfig
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.*

@Repository
class ProductRepository(private val producerConfig: KafkaProducerConfig) {
    private val props = Properties()

    init {
        props[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = producerConfig.host
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
    }

    private val kafka = KafkaProducer<String, String>(props)

    fun create(product: Product) {
        val mapper = jacksonObjectMapper()
        val msg: String = mapper.writeValueAsString(product)
        send(producerConfig.topic, msg, Instant.now().toEpochMilli(), product.uuid.toString(), lazy {})
    }

    private fun send(topic: String, msg: String, timestamp: Long, key: String?, callback: Lazy<Unit>) {
        kafka.send(ProducerRecord(topic, null, timestamp, key, msg), KafkaCallback(callback))
        kafka.flush()
    }
}
