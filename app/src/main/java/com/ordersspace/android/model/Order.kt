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

    enum class Status {
        /** Создан, но еще не оплачен */
        CREATED,
        /** Оплачен */
        PAID,
        /** Готовится */
        PREPARING,
        /** Готов (на кассе или еще не дошел до доставки) */
        READY,
        /** В доставке (если нужно) */
        DELIVERED,
        /** Получен, закрыт */
        RECEIVED;

        companion object {

            fun String.toStatus(): Status? = values().find { it.name.equals(this, true) }
        }
    }
}
