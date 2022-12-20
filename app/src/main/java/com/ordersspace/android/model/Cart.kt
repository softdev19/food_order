package com.ordersspace.android.model

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import com.ordersspace.android.ui.customer.ItemCard
import com.ordersspace.android.ui.navigation.CustomerRoutes.item
import com.ordersspace.android.ui.rememberCustomer
import kotlinx.coroutines.launch



object Cart {
    val items = mutableMapOf<ULong, Int>()
    operator fun contains(itemId: ULong) = itemId in items


    operator fun plusAssign(itemId: ULong) {
        if (itemId !in items) {
            items[itemId] = 0
        }
        items[itemId] = items[itemId]!! + 1
    }

    operator fun minusAssign(itemId: ULong) {
        items[itemId] = items[itemId]!! - 1
        if (items[itemId] == 0) {
            items -= itemId
        }
    }


    operator fun get(itemId: ULong): Int {
        return items[itemId] ?: 0
    }

    inline fun forEach(action: (itemId: ULong) -> Unit) {
        items.forEach { (id, _) -> action(id) }
    }
}