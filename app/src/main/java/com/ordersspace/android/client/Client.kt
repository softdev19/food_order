package com.ordersspace.android.client

import com.ordersspace.android.model.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.HttpStatusCode.Companion.OK
import kotlinx.serialization.Serializable

abstract class Client<U : User>(val name: String, val password: String) {

    protected abstract val client: HttpClient

     abstract suspend fun authenticate(): U?

     abstract suspend fun register(phone: String?, email: String?): U?

    protected suspend inline fun <reified T> getObject(path: String): T? {
        val response = client.get(path)
        if (response.status != OK) return null
        return response.body<T>()
    }

    protected suspend inline fun <reified T> getList(path: String): List<T> {
        val response = client.get(path)
        if (response.status != OK) return emptyList()
        return response.body()
    }

    companion object {

        const val baseUrl = "http://10.0.2.2:8080"

        val String.isValidPassword: Boolean get() = length >= 8
                && any { it.isDigit() }
                && any { it.isLetter() && it.isUpperCase() }
                && any { it.isLetter() && it.isLowerCase() }
                && any { !it.isLetterOrDigit() }

        val String.isValidName: Boolean get() = "[A-Za-zА-Яа-я0-9_\\-]+".toRegex() matches this
    }
}