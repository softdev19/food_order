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

     abstract suspend fun signup(phone: String?, email: String?): U?

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

        val String.isValidPhone: Boolean get() = "\\+\\d+".toRegex() matches this

        val String.isValidEmail: Boolean get() = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])".toRegex() matches this
    }
}