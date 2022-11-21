package com.ordersspace.android.model

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Client(val name: String,val password: String) {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(name,this@Client.password)
                }
                realm = "Order's space auth"
            }
        }
    }

    suspend fun authenticate(): User? {
        val response = client.get("$baseUrl/auth")
        if (response.status != OK)
            return null
        return response.body<User>()
    }


    suspend fun register(
        phone: String?,
        email: String?,
    ):User? {
        val response = client.post("${baseUrl}/register") {
            parameter("name", name)
            parameter("password", password)
            parameter("phone", phone)
            parameter("email", email)
        }
        if (response.status != OK)
            return null
        return response.body<User>()
    }

    companion object {
        fun addUser(name: String, phone: String?, email: String?) {

        }

        const val baseUrl = "http://10.0.2.2:8080"
    }
    internal fun isValidPassword(password: String): Boolean {
        if (password.length > 8) return true
        if (password.filter { it.isDigit() }.firstOrNull() !== null) return true
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() !== null) return true
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() !== null) return true
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() !== null) return true
        return false
    }
    fun accuracyPassword(password: String){
        "[A-Za-zА-Яа-я0-9_\\-]+".toRegex() matches password
    }
}
