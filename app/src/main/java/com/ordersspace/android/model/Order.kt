package com.ordersspace.android.model

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: ULong,
    val customerId: ULong,
    val placeId: ULong,
    val status: Status,
    val total: Double,
    /** Время заказа в миллисекундах */
    val timestamp: Long,
) {

    enum class Status(val displayName: String) {
        /** Создан, но еще не оплачен */
        CREATED("Создан"),
        /** Оплачен */
        PAID("Оплачен"),
        /** Готовится */
        PREPARING("Готовится"),
        /** Готов (на кассе или еще не дошел до доставки) */
        READY("Готов"),
        /** В доставке (если нужно) */
        DELIVERED("В доставке"),
        /** Получен, закрыт */
        RECEIVED("Получен");

        companion object {

            fun String.toStatus(): Status? = values().find { it.name.equals(this, true) }
        }
    }
}
