package com.ordersspace.android.model

import kotlinx.serialization.Serializable

@Serializable
data class InventoryItem(
    val id: ULong,
    val name: String,
    val measure: AmountMeasure,
    val amount: Int,
    val placeId: ULong,
) {
    enum class AmountMeasure {
        PIECES, WEIGHT, VOLUME
    }
}
