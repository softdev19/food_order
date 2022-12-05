package com.ordersspace.android.ui.admin

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.ordersspace.android.R
import com.ordersspace.android.model.Network
import com.ordersspace.android.ui.AdminPage
import com.ordersspace.android.ui.navigation.CustomerRoutes.place
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import java.util.jar.Attributes.Name

val networks = listOf(
    Network(
        1UL,
        "hello",
        "world",
        "https://media.discordapp.net/attachments/886662838322614364/1048633120968146944/image.png",
        0UL,
    ),
)
@Preview(showSystemUi = true, name = "Networks Page")
@Composable
fun NetworksPagePreview() {
    OrdersSpaceTheme {
        NetworksPage(networks = networks)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NetworksPage(controller: NavController? = null, networks: List<Network>) {
    val navController = rememberNavController()
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Ваши сети") })
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { /* ... */ },

            ) {
            FabPosition.Center
            Icon(Icons.Filled.Add, "Localized description")
        }
    },) { padding ->
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
        onClick = { /* Do something */ }, modifier = Modifier.fillMaxWidth()

    ) {

        Box() {
            Column() {
                Text(text = network.name)
                Image(
                    painter = rememberImagePainter(network.imageUrl),
                    contentDescription = "Описание"
                )
                Text(text = network.description)
            }
        }
    }

}






