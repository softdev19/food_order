package com.ordersspace.android.ui.theme

import com.ordersspace.android.model.ItemType

@kotlinx.serialization.Serializable
data class Item (
    val id: ULong,
    val name: String,
    val type: ItemType,
    val cost: Float,
    val weight: Float,
    val volume: Float,
    val description: String,
    val isagerestricted: Boolean,
    val imageurl: String,
        )
fun dishesCard(){

}
