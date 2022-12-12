package com.ordersspace.android.model

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val id : ULong,
    val name: String,
    val surname : String,
    val date : Double,
    val gender : String,
    val phone : ULong,
    val email : String,
) : User()
