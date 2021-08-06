package com.example.shoppingcart.repository

import com.example.shoppingcart.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ShoppingCartRepository : CrudRepository<Product, Long> {
    fun findByName(name: String)
}
