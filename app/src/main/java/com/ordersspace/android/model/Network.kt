package com.ordersspace.android.model

import kotlinx.serialization.Serializable

@Serializable
data class Network(
    val id: ULong,
    val name: String,
    val description: String,
    val imageUrl: String,
    val ownerId: ULong,
)
