package com.example.webshop.model

import java.util.*

data class Product(
    val id: Long,
    val uuid: UUID,
    val name: String,
    val displayName: String?,
    val price: Float,
    val isEnabled: Boolean) {
    constructor(id: Long, name: String, displayName: String?, price: Float, isEnabled: Boolean)
            : this(id, UUID.randomUUID(), name, displayName, price, isEnabled)
}
