package com.ordersspace.android.client

import com.ordersspace.android.model.Customer
import com.ordersspace.android.model.MenuItem
import com.ordersspace.android.model.Order
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class CustomerClient(name: String, password: String) : Client<Customer>(name, password) {

    override val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(name, this@CustomerClient.password)
                }
                realm = "Order's space auth"
            }
        }
    }

    override suspend fun authenticate(): Customer? = getObject("$baseUrl/auth")

    override suspend fun signup(
        phone: String?,
        email: String?,
    ): Customer? {
        val response = client.post("$baseUrl/signup") {
            parameter("name", name)
            parameter("password", password)
            parameter("phone", phone)
            parameter("email", email)
        }
        if (response.status != OK) return null
        return response.body<Customer>()
    }

    suspend fun getOrders(): List<Order> = getList("$baseUrl/orders")

    suspend fun getCurrentOrders(): List<Order> = getList("$baseUrl/orders/current")

    suspend fun makeOrder(
        placeId: ULong,
        total: Double,
        timestamp: Long,
    ): Order? {
        val response = client.post("$baseUrl/orders/create") {
            parameter("placeId", placeId)
            parameter("total", total)
            parameter("timestamp", timestamp)
        }
        if (response.status != OK) return null
        return response.body<Order>()
    }

    suspend fun getOrder(id: ULong): Order? = getObject("$baseUrl/orders/$id")

    suspend fun editOrder(
        id: ULong,
        status: Order.Status,
    ): Order? {
        val response = client.patch("$baseUrl/orders/$id/edit") {
            parameter("status", status)
        }
        if (response.status != OK) return null
        return response.body<Order>()
    }

    suspend fun getMenuItem(
        id: ULong
    ): MenuItem? = getObject("$baseUrl/menu/$id")

    companion object {
        private const val baseUrl = "${Client.baseUrl}/customer"
    }
}