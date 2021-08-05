package com.example.webshop.producer

import com.example.webshop.config.KafkaProducerConfig
import com.example.webshop.model.Product
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.kafka.clients.producer.KafkaProducer as ApacheKafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.streams.StreamsConfig
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class KafkaProducer(private val producerConfig: KafkaProducerConfig) {
    private val producer: ApacheKafkaProducer<String, String>
    private val props = Properties()

    init {
        props[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = producerConfig.host
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        producer = ApacheKafkaProducer<String, String>(props)
    }

    fun createProduct(product: Product) {
        val mapper = jacksonObjectMapper()
        val msg: String = mapper.writeValueAsString(product)
        send(producerConfig.topic, msg, Instant.now().toEpochMilli(), product.uuid.toString(), lazy {})
    }

    private fun send(topic: String, msg: String, timestamp: Long, key: String?, callback: Lazy<Unit>) {
        producer.send(ProducerRecord(topic, null, timestamp, key, msg), KafkaCallback(callback))
        producer.flush()
    }
}
