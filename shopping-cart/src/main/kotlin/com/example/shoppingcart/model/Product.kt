package com.example.shoppingcart.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.util.*

@RedisHash
class Product(
    @Id
    val id: Long,
    val uuid: UUID = UUID.randomUUID(),
    val name: String,
    val displayName: String?,
    val price: Float,
    val isEnabled: Boolean)
