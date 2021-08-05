package com.example.shoppingcart.consumer

import com.example.shoppingcart.config.KafkaConsumerConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.KStream
import org.springframework.stereotype.Service
import java.util.Properties

@Service
class KafkaConsumer(consumerConfig: KafkaConsumerConfig) {
    private val props = Properties()
    private val builder = StreamsBuilder()
    private val shoppingCartAddStream: KStream<String, String>

    init {
        props[StreamsConfig.APPLICATION_ID_CONFIG] = consumerConfig.groupId
        props[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = consumerConfig.host
        props[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String().javaClass
        props[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String().javaClass
        shoppingCartAddStream = builder.stream(consumerConfig.topics)
    }

    fun shoppingCartAddStream() = shoppingCartAddStream

    fun watch() {
        KafkaStreams(builder.build(), props).start()
    }
}