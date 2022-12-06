package com.ordersspace.android.model

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val id: ULong,
    val name: String,
    val phone: String?,
    val email: String?,
) : User()
