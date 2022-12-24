@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.customer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.model.Cart
import com.ordersspace.android.model.MenuItemCard
import com.ordersspace.android.ui.Defaults
import com.ordersspace.android.ui.navigation.CustomerRoutes
import com.ordersspace.android.ui.rememberCustomer
import com.ordersspace.android.ui.theme.OrdersSpaceTheme

@Composable
fun CartPage(controller: NavHostController) {
    val customer = rememberCustomer()
    val items = remember { mutableStateListOf<MenuItemCard>() }
    var key by remember { mutableStateOf(Any()) }

    LaunchedEffect(0) {
        items.addAll(customer?.getCart(Cart.filterValues { it != 0 }.keys.toList()).orEmpty())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { Defaults.BackButton(controller::popBackStack) },
                title = { Text("Корзина") },
                actions = {
                    IconButton(onClick = { Cart.clear(); key = Any() }) {
                        Icon(Icons.Outlined.Delete, "Очистить")
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar {
                Spacer(Modifier.width(16.dp))
                Text(
                    "Итого: %.2f".format(items.sumOf { it.cost * (Cart[it.id] ?: 0) }.also { key }),
                    style = MaterialTheme.typography.headlineSmall,
                )
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = { controller.navigate(CustomerRoutes.checkout) },
                    enabled = items.isNotEmpty(),
                ) {
                    Text("Перейти к оплате")
                }
                Spacer(Modifier.width(16.dp))
            }
        },
    ) { padding ->
        if (items.isEmpty()) Box(Modifier.fillMaxSize(), Alignment.Center) {
            Text("Корзина пуста")
        } else LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(items, key = { it.id.toLong() }) { item ->
                if (Cart[item.id] == 0) {
                    Cart -= item.id
                    return@items
                }
                MenuItemCard(item, onValueChanged = { key = Any() })
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CartPagePreview() = OrdersSpaceTheme { CartPage(rememberNavController()) }