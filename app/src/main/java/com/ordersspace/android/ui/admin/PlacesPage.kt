@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ordersspace.android.client.AdminClient
import com.ordersspace.android.client.ClientStorage
import com.ordersspace.android.model.Admin
import com.ordersspace.android.model.Place
import com.ordersspace.android.ui.Defaults
import com.ordersspace.android.ui.navigation.AdminRoutes
import com.ordersspace.android.ui.rememberAdmin
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import com.ordersspace.android.ui.withArgs

@Composable
fun PlacesPage(
    controller: NavController,
    networkId: Long = -1,
) {
    val admin = rememberAdmin()
    val places = remember { mutableStateListOf<Place>() }
    var key by remember { mutableStateOf(0) }

    LaunchedEffect(key) {
        places.addAll(admin?.getPlaces(networkId.toULong()).orEmpty())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Рестораны сети") },
                navigationIcon = { Defaults.BackButton(controller::popBackStack) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    controller.navigate(AdminRoutes.place.withArgs("id" to networkId, "pid" to -1L))
                },
            ) {
                FabPosition.Center
                Icon(Icons.Filled.Add, "Добавить ресторан")
            }
        },
    ) { padding ->
        if (places.isEmpty()) Box(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                "В сети пока нет ресторанов. Нажмите + внизу экрана, чтобы добавить ресторан.",
                textAlign = TextAlign.Center,
            )
        } else LazyColumn(Modifier.padding(padding)) {
            items(places, { it.id.toLong() }) { place ->
                RestaurantCard(
                    place,
                    onClick = {
                        controller.navigate(
                            AdminRoutes.place.withArgs("id" to networkId, "pid" to place.id)
                        )
                    },
                )
            }
        }
    }
}

@Composable
fun RestaurantCard(
    place: Place,
    onClick: () -> Unit = {},
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            AsyncImage(
                model = place.imageUrl,
                contentDescription = "Сеть",
                modifier = Modifier
                    .aspectRatio(1.78f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                placeholder = ColorPainter(Color(0, 0, 0, 20)),
                contentScale = ContentScale.Crop,
            )
            Spacer(Modifier.height(16.dp))
            Text(text = place.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(8.dp))
            Text(text = place.description)
        }
    }
}

@Composable
@Preview(showSystemUi = true, name = "Places Page")
fun PlacesPagePreview() = OrdersSpaceTheme { PlacesPage(rememberNavController()) }

@Composable
@Preview(name = "Place card")
fun RestaurandCardPreview() = OrdersSpaceTheme {
    RestaurantCard(
        Place(
            0UL,
            "Тестовый ресторан",
            "Этот ресторан предназначен для предпросмотра карточки ресторана",
            "https://media.discordapp.net/attachments/694793651938918410/989694801559560202/unknown.png",
            0UL,
        )
    )
}