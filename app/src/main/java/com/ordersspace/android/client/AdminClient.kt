package com.ordersspace.android.client

import com.ordersspace.android.model.Admin
import com.ordersspace.android.model.MenuItem
import com.ordersspace.android.model.Network
import com.ordersspace.android.model.Place
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class AdminClient(name: String, password: String) : Client<Admin>(name, password) {

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
                    BasicAuthCredentials(name, this@AdminClient.password)
                }
                realm = "Order's space place owner/admin"
            }
        }
    }

    override suspend fun authenticate(): Admin? = getObject("$baseUrl/auth")

    override suspend fun signup(
        phone: String?,
        email: String?,
    ): Admin? {
        val response = client.post("$baseUrl/signup") {
            parameter("name", name)
            parameter("password", password)
            parameter("phone", phone)
            parameter("email", email)
        }
        if (response.status != OK) return null
        return response.body<Admin>()
    }

    suspend fun getNetworks(): List<Network> = getList("$baseUrl/networks")

    suspend fun createNetwork(
        name: String,
        description: String,
        imageUrl: String,
    ): Network? {
        val response = client.post("$baseUrl/networks/create") {
            parameter("name", name)
            parameter("description", description)
            parameter("imageUrl", imageUrl)
        }
        if (response.status != OK) return null
        return response.body<Network>()
    }

    suspend fun getNetwork(id: ULong): Network? = getObject("$baseUrl/networks/$id")

    suspend fun editNetwork(
        id: ULong,
        name: String?,
        description: String?,
        imageUrl: String?
    ): Network? {
        val response = client.patch("$baseUrl/networks/$id/edit") {
            parameter("name", name)
            parameter("description", description)
            parameter("imageUrl", imageUrl)
        }
        if (response.status != OK) return null
        return response.body<Network>()
    }

    suspend fun getMenuItems(networkId: ULong): List<MenuItem> =
        getList("$baseUrl/networks/$networkId/items")

    suspend fun getMenuItem(networkId: ULong, itemId: ULong): MenuItem? =
        getObject("$baseUrl/networks/$networkId/items/$itemId")

    suspend fun createMenuItem(
        networkId: ULong,
        name: String,
        type: MenuItem.ItemType,
        cost: Double,
        weight: Double,
        volume: Double,
        description: String?,
        isAgeRestricted: Boolean,
        imageUrl: String?,
    ): MenuItem? {
        val response = client.post("$baseUrl/networks/$networkId/items/create") {
            parameter("name", name)
            parameter("type", type)
            parameter("cost", cost)
            parameter("weight", weight)
            parameter("volume", volume)
            parameter("description", description)
            parameter("isAgeRestricted", isAgeRestricted)
            parameter("imageUrl", imageUrl)
        }
        if (response.status != OK) return null
        return response.body<MenuItem>()
    }

    suspend fun getPlaces(networkId: ULong): List<Place> =
        getList("$baseUrl/networks/$networkId/places")

    suspend fun getPlace(networkId: ULong, placeId: ULong): Place? =
        getObject("$baseUrl/networks/$networkId/places/$placeId")

    suspend fun createPlace(
        networkId: ULong,
        name: String,
        description: String,
        imageUrl: String,
    ): Place? {
        val response = client.post("$baseUrl/networks/$networkId/places/create") {
            parameter("name", name)
            parameter("description", description)
            parameter("imageUrl", imageUrl)
        }
        if (response.status != OK) return null
        return response.body<Place>()
    }

    suspend fun editPlace(
        networkId: ULong,
        placeId: ULong,
        name: String?,
        description: String?,
        imageUrl: String?,
    ): Place? {
        val response = client.patch("$baseUrl/networks/$networkId/places/$placeId/edit") {
            parameter("name", name)
            parameter("description", description)
            parameter("imageUrl", imageUrl)
        }
        if (response.status != OK) return null
        return response.body<Place>()
    }

    suspend fun getMenu(networkId: ULong, placeId: ULong): List<MenuItem> =
        getList("$baseUrl/networks/$networkId/places/$placeId/menu")

    companion object {

        private const val baseUrl = "${Client.baseUrl}/admin"
    }
}