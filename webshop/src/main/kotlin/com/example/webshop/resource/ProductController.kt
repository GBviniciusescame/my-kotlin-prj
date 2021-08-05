package com.example.webshop.resource

import com.example.webshop.dto.ProductDto
import com.example.webshop.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/product")
class ProductController(private val service: ProductService) {
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun `post de alguns produtos`(@RequestBody products: Set<ProductDto>) {
        service.createProducts(products)
    }
}