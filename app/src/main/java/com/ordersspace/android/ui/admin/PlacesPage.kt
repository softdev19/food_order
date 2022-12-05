package com.ordersspace.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.R
import com.ordersspace.android.model.Network
import com.ordersspace.android.model.Place
import com.ordersspace.android.ui.theme.OrdersSpaceTheme


val places = listOf(
    Place(
        1UL,
        "hello",
        "world",
        "https://media.discordapp.net/attachments/886662838322614364/1048633120968146944/image.png",
        0UL,
    ),
)

@Preview(showSystemUi = true, name = "Places Page")
@Composable
fun PlacesPagePreview() {
    OrdersSpaceTheme {
        PlacesPage(places = places)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlacesPage(controller: NavController? = null, places: List<Place>) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Ваши рестораны") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* ... */ },

                ) {
                FabPosition.Center
                Icon(Icons.Filled.Add, "Localized description")
            }
        },
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(places, { it.id.toLong() }) { place ->
                RestaurantCard(place)
            }
        }
    }


}


@Composable
fun RestaurantCard(place: Place) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = CardDefaults.shape)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logot),
            contentDescription = "",
            modifier = Modifier
                .aspectRatio(1.78f)
                .fillMaxWidth()
        )
        Text(text = place.name, style = MaterialTheme.typography.headlineMedium)
        Text(text = place.description)
    }
}

