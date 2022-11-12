package com.ordersspace.android.model

import kotlinx.serialization.Serializable

@Serializable
data class Place(
    val id: ULong,
    val name: String,
    val description: String,
    val imageUrl: String
)
