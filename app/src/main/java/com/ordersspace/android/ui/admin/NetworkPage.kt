@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.admin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.ui.BottomAppBar
import com.ordersspace.android.ui.BottomNavContent
import com.ordersspace.android.ui.navigation.Route
import com.ordersspace.android.ui.theme.OrdersSpaceTheme

@Composable
fun NetworkPage(navController: NavHostController, networkId: Long) {
    val bottomNavController = rememberNavController()
    val pages = remember { Route.networkPagePages }
    val id = "id" to networkId

    Scaffold(
        bottomBar = { BottomAppBar(pages, bottomNavController, id) },
    ) {
        Box(Modifier.padding(it)) {
            BottomNavContent(pages, navController, bottomNavController, id)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun NetworkPagePreview() = OrdersSpaceTheme { NetworkPage(rememberNavController(), -1L) }
