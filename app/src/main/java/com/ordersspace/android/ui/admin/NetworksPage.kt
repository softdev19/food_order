@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.ordersspace.android.model.Network
import com.ordersspace.android.ui.navigation.AdminRoutes
import com.ordersspace.android.ui.rememberAdmin
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import com.ordersspace.android.ui.withArgs

@Composable
fun NetworksPage(controller: NavController) {
    val networks = remember { mutableStateListOf<Network>() }
    val admin = rememberAdmin()
    var key by remember { mutableStateOf(0) }

    LaunchedEffect(key) {
        networks.addAll(admin?.getNetworks().orEmpty())
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Ваши сети") }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { controller.navigate(AdminRoutes.network.withArgs("id" to -1)) },
            ) {
                FabPosition.Center
                Icon(Icons.Filled.Add, "Localized description")
            }
        },
    ) { padding ->
        if (networks.isEmpty()) Box(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                "У вас пока нет сетей. Нажмите + внизу экрана, чтобы создать сеть.",
                textAlign = TextAlign.Center
            )
        } else LazyColumn(Modifier.padding(padding)) {
            items(networks, { it.id.toLong() }) { network ->
                NetworkCard(
                    network,
                    onClick = { controller.navigate(AdminRoutes.network.withArgs("id" to network.id)) }
                )
            }
        }
    }
}

@Composable
fun NetworkCard(
    network: Network,
    onClick: () -> Unit = {},
) {
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

@Composable
@Preview(showSystemUi = true, name = "Networks Page")
fun NetworksPagePreview() = OrdersSpaceTheme { NetworksPage(rememberNavController()) }

@Composable
@Preview(name = "Network card")
fun NetworkCardPreview() = OrdersSpaceTheme {
    NetworkCard(
        network = Network(
            0UL,
            "Тестовая сеть",
            "Эта сеть предназначена для предпросмотра карточки сети",
            "https://media.discordapp.net/attachments/694793651938918410/989694801559560202/unknown.png",
            0UL,
        )
    )
}




