@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.customer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ordersspace.android.model.Network
import com.ordersspace.android.ui.rememberCustomer
import com.ordersspace.android.ui.theme.OrdersSpaceTheme

@Composable
fun PlaceMap(navController: NavHostController) {
    val customer = rememberCustomer()
    val networks = remember { mutableStateListOf<Network>() }

    LaunchedEffect(null) {
        networks.addAll(customer?.getNetworks().orEmpty())
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Рестораны") }) },
    ) { padding ->
        LazyColumn(Modifier.padding(padding)) {
            items(networks, key = { it.id.toLong() }) { network ->
                PlaceCard(network)
            }
        }
    }
}

@Composable
fun PlaceCard(network: Network, onClick: () -> Unit = {}) {
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            AsyncImage(
                model = network.imageUrl,
                contentDescription = "Сеть",
                modifier = Modifier
                    .aspectRatio(1.78f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                placeholder = ColorPainter(Color(0, 0, 0, 20)),
                contentScale = ContentScale.Crop,
            )
            Spacer(Modifier.height(16.dp))
            Text(network.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(8.dp))
            Text(network.description)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PlaceMapPreview() {
    OrdersSpaceTheme {
        PlaceMap(rememberNavController())
    }
}

@Preview
@Composable
fun PlaceCardPreview() = OrdersSpaceTheme {
    PlaceCard(
        Network(
            0UL,
            "test",
            "test",
            "test",
            0UL,
        )
    )
}