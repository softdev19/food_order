@file:Suppress("UNCHECKED_CAST")

package com.ordersspace.android.client

import com.ordersspace.android.model.User
import kotlin.reflect.KClass

object ClientStorage {

    private val clients = mutableMapOf<KClass<*>, Client<*>>()

    internal inline fun <reified U : User> get(): Client<U>? = clients[U::class] as? Client<U>

    internal inline fun <reified U : User> put(value: Client<U>) { clients[U::class] = value }
}