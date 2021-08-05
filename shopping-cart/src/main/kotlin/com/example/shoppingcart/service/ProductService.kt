package com.example.shoppingcart.service

import com.example.shoppingcart.consumer.KafkaConsumer
import com.example.shoppingcart.model.Product
import com.example.shoppingcart.repository.ProductRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service

@Service
class ProductService(
    kafkaConsumer: KafkaConsumer,
    val repository: ProductRepository
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