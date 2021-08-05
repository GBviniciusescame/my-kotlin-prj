package com.example.shoppingcart.repository

import com.example.shoppingcart.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findByName(name: String)
}