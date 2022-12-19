@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.customer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.ui.Defaults
import com.ordersspace.android.ui.theme.OrdersSpaceTheme

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
}
@Preview(showSystemUi = true)
@Composable
fun CartPagePreview() {
    OrdersSpaceTheme {
        CartPage(rememberNavController())
    }
}