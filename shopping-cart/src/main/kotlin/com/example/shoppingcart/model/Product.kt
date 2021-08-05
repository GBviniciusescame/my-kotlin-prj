package com.example.shoppingcart.model

import org.hibernate.Hibernate
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Product(
    @Id
    val id: Long,
    val uuid: UUID = UUID.randomUUID(),
    val name: String,
    val displayName: String?,
    val price: Float,
    val isEnabled: Boolean)
