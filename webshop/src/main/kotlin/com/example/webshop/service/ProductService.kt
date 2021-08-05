package com.example.webshop.service

import com.example.webshop.dto.ProductDto
import com.example.webshop.model.Product
import com.example.webshop.producer.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val repository: ProductRepository) {
    companion object {
        val products: Set<Product> = setOf (
            Product(id = 1, name = "Prod1", displayName = null, 10.5f, true),
            Product(id = 2, name = "Prod2", displayName = "Product 2", 12f, true),
            Product(id = 3, name = "Prod3", displayName = "Product 3", 3f, false)
        )
    }

    fun getProduct(id: Long): Product? =
        products.stream()
            .filter{ it.id == id }
            .filter{ it.isEnabled }
            .findFirst()
            .orElseThrow{ Exception("Product ($id) not found!") }

    fun createProducts(productsDto: Set<ProductDto>) {
        for (p in productsDto) {
            repository.create(getProduct(p.id)!!)
        }
    }
}