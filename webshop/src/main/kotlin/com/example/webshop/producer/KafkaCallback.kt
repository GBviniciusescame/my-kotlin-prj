package com.example.webshop.producer

import org.apache.kafka.clients.producer.Callback
import org.apache.kafka.clients.producer.RecordMetadata
import java.lang.Exception

class KafkaCallback(private val callback: Lazy<Unit>) : Callback {
    override fun onCompletion(metadata: RecordMetadata?, exception: Exception?) {
        println(callback.value)
    }
}
