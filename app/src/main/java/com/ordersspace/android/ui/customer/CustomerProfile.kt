package com.ordersspace.android.ui.customer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ordersspace.android.model.Order
import com.ordersspace.android.ui.navigation.Route
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import com.ordersspace.android.ui.toDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerProfile(
    onPageClicked: (route: String) -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Профиль") },
            )
        },
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            item {
                // TODO: заменить на реальный заказ
                CurrentOrderCard(order = Order(
                    123UL,
                    123UL,
                    123UL,
                    Order.Status.CREATED,
                    69.99,
                    System.currentTimeMillis()
                ))
            }
            // TODO: текущие заказы
            items(Route.customerProfilePages) { page ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onPageClicked(page.route) }
                        .padding(16.dp)
                ) {
                    Icon(page.icon, page.name)
                    Box(Modifier.width(16.dp))
                    Text(page.name, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
fun CurrentOrderCard(order: Order) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("Заказ №${order.id}", style = MaterialTheme.typography.headlineSmall)
            Text("Статус: ${order.status.displayName}")
            Text("Создан: ${order.timestamp.toDate()}")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CustomerProfilePreview() = OrdersSpaceTheme { CustomerProfile() }
