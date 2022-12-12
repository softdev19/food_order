package com.ordersspace.android.ui.admin

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.ordersspace.android.client.ClientStorage
import com.ordersspace.android.model.Network
import com.ordersspace.android.ui.navigation.AdminRoutes
import com.ordersspace.android.ui.theme.OrdersSpaceTheme

@Preview(showSystemUi = true, name = "Networks Page")
@Composable
fun NetworksPagePreview() {
    OrdersSpaceTheme {
        NetworksPage()
    }
}

val mockNetworks = listOf(
    Network(
        1UL,
        "hello",
        "world",
        "https://media.discordapp.net/attachments/886662838322614364/1048633120968146944/image.png",
        0UL,
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NetworksPage(controller: NavController? = null) {
    val networks = remember {
        mutableStateListOf<Network>()
    }
    val admin = ClientStorage.admin
    LaunchedEffect(key1 = admin) {
        networks.addAll(admin?.getNetworks() ?: mockNetworks)
    }

    val navController = rememberNavController()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Ваши сети") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { controller?.navigate(AdminRoutes.networks + "/-1") },
                ) {
                FabPosition.Center
                Icon(Icons.Filled.Add, "Localized description")
            }
        },
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(networks, { it.id.toLong() }) { network ->
                NetworkCard(network)
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkCard(network: Network) {
    OutlinedCard(
        onClick = { /* Do something */ }, modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {

        Box() {
            Column() {
                Text(text = network.name, modifier = Modifier.padding(16.dp))
                Image(
                    painter = rememberImagePainter(network.imageUrl),
                    contentDescription = "Описание"
                )
                Text(text = network.description,modifier = Modifier.padding(16.dp))
            }
        }
    }
}







