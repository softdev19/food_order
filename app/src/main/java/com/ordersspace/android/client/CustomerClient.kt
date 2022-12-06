package com.ordersspace.android.client

import com.ordersspace.android.model.Customer
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
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

    override suspend fun authenticate(): Customer? {
        val response = client.get("$baseUrl/auth")
        if (response.status != HttpStatusCode.OK)
            return null
        return response.body<Customer>()
    }

    override suspend fun register(
        phone: String?,
        email: String?,
    ): Customer? {
        val response = client.post("$baseUrl/register") {
            parameter("name", name)
            parameter("password", password)
            parameter("phone", phone)
            parameter("email", email)
        }
        if (response.status != HttpStatusCode.OK)
            return null
        return response.body<Customer>()
    }

    companion object {

        private const val baseUrl = "${Client.baseUrl}/customer"
    }
}