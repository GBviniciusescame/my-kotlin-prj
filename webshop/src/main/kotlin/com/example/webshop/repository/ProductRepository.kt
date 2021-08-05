package com.example.webshop.repository

import com.example.webshop.model.Product
import com.example.webshop.producer.KafkaProducer
import org.springframework.stereotype.Repository

@Repository
class ProductRepository(val producer: KafkaProducer) {
    fun create(product: Product) {
        producer.createProduct(product)
    }
}