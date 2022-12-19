@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ordersspace.android.model.MenuItem
import com.ordersspace.android.ui.Defaults
import com.ordersspace.android.ui.navigation.AdminRoutes
import com.ordersspace.android.ui.rememberAdmin
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import com.ordersspace.android.ui.withArgs

@Composable
fun NetworkMenuPage(controller: NavHostController, networkId: Long = -1L) {
    val admin = rememberAdmin()
    val items = remember { mutableStateListOf<MenuItem>() }
    var saveKey by remember { mutableStateOf(0) }

    LaunchedEffect(saveKey) {
        items.clear()
        if (networkId != -1L) items.addAll(admin?.getMenuItems(networkId.toULong()).orEmpty())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Блюда сети") },
                navigationIcon = { Defaults.BackButton(controller::popBackStack) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    controller.navigate(AdminRoutes.editItem.withArgs("id" to networkId, "iid" to -1L))
                },
            ) {
                Icon(Icons.Outlined.Add, "Добавить")
            }
        },
    ) { padding ->
        if (items.isEmpty()) Box(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(), Alignment.Center) {
            Text(
                "В этой сети еще нет блюд. Нажмите + внизу экрана, чтобы добавить блюдо.",
                textAlign = TextAlign.Center,
            )
        } else LazyColumn(
            Modifier
                .padding(padding)
                .padding(16.dp)) {
            items(items, key = { it.id.toLong() }) {
                MenuItemCard(item = it) {
                    controller.navigate(AdminRoutes.editItem.withArgs("id" to networkId, "iid" to it.id))
                }
            }
        }
    }
}

@Composable
fun MenuItemCard(item: MenuItem, onClick: () -> Unit = {}) {
    Card(onClick) {
        Row(Modifier.padding(16.dp), Arrangement.spacedBy(16.dp)) {
            BadgedBox(badge = { Badge { Text(item.cost.toString()) } }) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = "Товар",
                    modifier = Modifier
                        .height(72.dp)
                        .aspectRatio(1.0f)
                        .clip(RoundedCornerShape(8.dp)),
                    placeholder = ColorPainter(Color(0, 0, 0, 20)),
                    contentScale = ContentScale.Crop,
                )
            }
            Column(Modifier.weight(1.0f, true), Arrangement.spacedBy(8.dp)) {
                Text(item.name, style = MaterialTheme.typography.headlineMedium)
                Text(item.description.orEmpty())
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun NetworkMenuPagePreview() = OrdersSpaceTheme { NetworkMenuPage(rememberNavController()) }

@Composable
@Preview
fun MenuItemCardPreview() = OrdersSpaceTheme {
    MenuItemCard(
        item = MenuItem(
            id = 0UL,
            name = "gugububrbger",
            type = MenuItem.ItemType.DISH,
            cost = 69.99,
            weight = 69.99,
            volume = 69.99,
            description = "best food ever",
            isAgeRestricted = false,
            imageUrl = "",
            networkId = 0UL,
        )
    )
}