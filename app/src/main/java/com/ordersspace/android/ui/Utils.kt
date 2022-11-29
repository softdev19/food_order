package com.ordersspace.android.ui

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ordersspace.android.ui.navigation.Page

@Composable
fun BottomAppBar(
    items: List<Page>,
    navController: NavHostController,
) = BottomAppBar {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val route = backStackEntry?.destination?.route
    items.forEach {
        NavigationBarItem(
            icon = { Icon(it.icon, null) },
            label = { Text(it.label) },
            selected = route == it.route,
            alwaysShowLabel = false,
            onClick = {
                navController.navigate(it.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) { saveState = true }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
        )
    }
}

@Composable
fun BottomNavContent(
    items: List<Page>,
    navController: NavHostController,
    bottomNavController: NavHostController,
) = NavHost(
    bottomNavController,
    items.firstOrNull()?.route.orEmpty(),
) {
    items.forEach { page ->
        composable(page.route) {
            page.content.invoke(navController)
        }
    }
}