@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.model.Cart
import com.ordersspace.android.model.MenuItem
import com.ordersspace.android.ui.Defaults
import com.ordersspace.android.ui.navigation.CustomerRoutes.item
import com.ordersspace.android.ui.rememberCustomer
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import kotlinx.coroutines.launch

@Composable
fun CartPage(controller: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { Defaults.BackButton(controller::popBackStack) },
                title = { Text("Корзина") },
                actions = {

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Outlined.Delete, "Очистить")
                    }
                },
            )
        }
    ) {
        it
    }
    Column() {
        val customer = rememberCustomer()
        val scope = rememberCoroutineScope()

        Cart.forEach { id ->
            var item by remember { mutableStateOf<MenuItem?>(null) }
            scope.launch {
                if (customer != null) {
                    item = customer.getMenuItem(id)
                }
            }
            if (item == null) return@forEach
            ItemCard(item = item!!) {

            }
        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun CartPagePreview() {
    OrdersSpaceTheme {
        CartPage(rememberNavController())
    }
}