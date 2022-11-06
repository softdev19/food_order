package com.ordersspace.android.model

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

object Client {
    private val client = HttpClient()
    const val baseUrl = "http://10.0.2.2:8080"

    suspend fun getUsers(): List<User> {
        val users = client.get("$baseUrl/users").body<List<User>>()
        return users
    }

    suspend fun addUser(
        name: String,
        phone: String?,
        email: String?,
    ) {
        client.post("$baseUrl/users") {
            parameter("name", name)
            parameter("phone", phone)
            parameter("email", email)
        }
    }
}
