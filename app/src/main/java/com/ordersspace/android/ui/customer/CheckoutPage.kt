@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.customer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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
fun CheckoutPage(controller: NavHostController) {
    val customer = rememberCustomer()
    val items = remember { mutableStateListOf<MenuItemCard>() }

    LaunchedEffect(0) {
        items.addAll(customer?.getCart(Cart.filterValues { it != 0 }.keys.toList()).orEmpty())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Оформление заказа") },
                navigationIcon = { Defaults.BackButton(controller::popBackStack) },
            )
        },
        bottomBar = {
            BottomAppBar {
                Spacer(Modifier.width(16.dp))
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = {
                        Cart.clear()
                        controller.navigate(CustomerRoutes.order)
                    },
                    enabled = items.isNotEmpty(),
                ) {
                    Text("Заказать")
                }
                Spacer(Modifier.width(16.dp))
            }
        }
    ) { padding ->
        LazyColumn(
            Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(items, key = { it.id.toLong() }) { item ->
                Row {
                    Text(item.name)
                    Spacer(Modifier.weight(1f))
                    Text("%.2f x %d = %.2f".format(item.cost, Cart[item.id], item.cost * (Cart[item.id] ?: 0)))
                }
            }
            item { Divider() }
            item {
                Text(
                    "Итого: %.2f".format(items.sumOf { it.cost * (Cart[it.id] ?: 0) }),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                )
            }
            item { Spacer(Modifier.height(16.dp)) }
            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "Билеты банка приколов",
                    onValueChange = {},
                    enabled = false,
                    label = { Text("Способ оплаты") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun CheckoutPagePreview() = OrdersSpaceTheme { CheckoutPage(rememberNavController()) }