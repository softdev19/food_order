@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.customer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import com.ordersspace.android.model.Cart
import com.ordersspace.android.model.MenuItemCard
import com.ordersspace.android.ui.navigation.CustomerRoutes
import com.ordersspace.android.ui.rememberCustomer
import com.ordersspace.android.ui.theme.OrdersSpaceTheme

@Composable
fun MenuPage(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text("Меню") },
                actions = {
                    IconButton(onClick = { navController.navigate(CustomerRoutes.cart) }) {
                        BadgedBox(badge = { Badge { Text("0") } }) {
                            Icon(Icons.Outlined.ShoppingCart, "Корзина")
                        }
                    }
                },
            )
        },
    ) { padding ->
        val client = rememberCustomer()
        val items = remember { mutableStateListOf<MenuItemCard>() }

        LaunchedEffect(0) {
            items.addAll(client?.getMenuItems().orEmpty())
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(items, key = { it.id.toLong() }) { item ->
                MenuItemCard(item, onClick = { /* TODO: navigate to item page */ })
            }
        }
    }
}

@Composable
fun MenuItemCard(item: MenuItemCard, onClick: () -> Unit = {}, onValueChanged: (Int) -> Unit = {}) {
    var amount by remember { mutableStateOf(Cart[item.id] ?: 0) }
    Card(onClick) {
        Row(Modifier.padding(16.dp, 16.dp, 16.dp, 0.dp), Arrangement.spacedBy(16.dp)) {
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
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(item.name, style = MaterialTheme.typography.headlineSmall)
                Text(item.description.orEmpty())
            }
        }
        Row(Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp), Arrangement.spacedBy(8.dp), Alignment.CenterVertically) {
            Icon(Icons.Outlined.Storefront, null)
            Text(item.networkName)
            Spacer(Modifier.weight(1f))
            CartButton(item, amount) { amount = it; Cart[item.id] = it; onValueChanged(it) }
        }
    }
}

@Composable
fun CartButton(
    item: MenuItemCard,
    value: Int,
    onValueChanged: (Int) -> Unit,
) {
    if (value == 0) {
        TextButton(onClick = { onValueChanged(1) }) {
            Icon(Icons.Outlined.AddShoppingCart, "Добавить в корзину")
            Spacer(Modifier.width(8.dp))
            Text(item.cost.toString())
        }
    } else {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { onValueChanged(value - 1) }) {
                Icon(Icons.Outlined.Remove, "Уменьшить количество товара")
            }
            Text(value.toString())
            IconButton(onClick = { onValueChanged(value + 1) }) {
                Icon(Icons.Outlined.Add, "Увеличить количество товара")
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun MenuPagePreview() = OrdersSpaceTheme { MenuPage(rememberNavController()) }

@Composable
@Preview
fun MenuItemCardPreview() = OrdersSpaceTheme {
    MenuItemCard(
        item = MenuItemCard(
            0UL,
            "burger",
            "best food ever",
            "bugrger king",
            "https://i.imgur.com/L5IhOun.png",
            69.99,
        ),
    )
}