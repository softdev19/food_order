package com.ordersspace.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.ui.theme.OrdersSpaceTheme


@Preview(showSystemUi = true, name = "Admin page")
@Composable
fun AdminMenuPagePreview() {
    OrdersSpaceTheme {
        AdminMenuPage()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminMenuPage(controller: NavController? = null) {

    val navController = rememberNavController()
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Меню") })

    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { /* ... */ },

        ) {
            FabPosition.Center
            Icon(Icons.Filled.Add, "Localized description")
        }
    },

        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                NavigationBarItem(icon = {
                    Icon(
                        Icons.Filled.RestaurantMenu, contentDescription = "Favorite"
                    )

                }, selected = false, onClick = {}, label = { Text(text = "Информация") })
                NavigationBarItem(icon = {
                    Icon(
                        Icons.Filled.ListAlt, contentDescription = "Favorite"
                    )

                }, selected = false, onClick = {}, label = { Text(text = "Меню") })


            }
        }) {

    }
}

@Composable
fun NavigationPage(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationPage.AdminMenuPage.route,
    ) {
        composable(SettingsItemPage.route) {
            AdminMenuPage()
        }
    }
}

object AdminMenuPage {
    const val route = "/adminMenuPage"
}
