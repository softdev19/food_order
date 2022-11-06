package com.ordersspace.android.model

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object Client {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    const val baseUrl = "http://10.0.2.2:8080"

    suspend fun getUsers(): List<User> {
        return client.get("$baseUrl/users").body()
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
