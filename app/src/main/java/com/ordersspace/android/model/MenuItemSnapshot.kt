package com.ordersspace.android.model

import kotlinx.serialization.Serializable

@Serializable
data class MenuItemSnapshot(
    val id: ULong,
    val name: String,
    val cost: Double,
    val imageUrl: String?,
    val menuItemId: ULong,
    val orderId: ULong,
)