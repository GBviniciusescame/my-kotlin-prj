package com.example.shoppingcart.service

import com.example.shoppingcart.consumer.KafkaWebshopConsumer
import com.example.shoppingcart.model.Product
import com.example.shoppingcart.repository.ShoppingCartRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service

@Service
class ShoppingCartService(
    kafkaConsumer: KafkaWebshopConsumer,
    val repository: ShoppingCartRepository
) {
    init {
        val mapper = jacksonObjectMapper()
        kafkaConsumer.shoppingCartAddStream().foreach { k, v ->
            val product = mapper.readValue(v, Product::class.java)
            createProduct(product)
        }
        kafkaConsumer.watch()
    }

    fun createProduct(product: Product) {
        repository.save(product)
    }
}