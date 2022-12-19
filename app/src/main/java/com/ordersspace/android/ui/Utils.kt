package com.ordersspace.android.ui

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.core.os.bundleOf
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ordersspace.android.client.AdminClient
import com.ordersspace.android.client.ClientStorage
import com.ordersspace.android.client.CustomerClient
import com.ordersspace.android.model.Admin
import com.ordersspace.android.model.Customer
import com.ordersspace.android.model.Order
import com.ordersspace.android.model.Order.Status.*
import com.ordersspace.android.ui.navigation.Route
import java.util.*

@Composable
fun BottomAppBar(
    items: List<Route.ScreenPartRoute>,
    navController: NavHostController,
    vararg args: Pair<String, Any?>,
) = BottomAppBar {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val route = backStackEntry?.destination?.route
    items.forEach {
        NavigationBarItem(
            icon = { Icon(it.icon, null) },
            label = { Text(it.name) },
            selected = route == it.route,
            alwaysShowLabel = false,
            onClick = {
                navController.navigate(it.route.withArgs(*args)) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
        )
    }
}

@Composable
fun BottomNavContent(
    items: List<Route.ScreenPartRoute>,
    navController: NavHostController,
    bottomNavController: NavHostController,
    vararg args: Pair<String, Any?>,
) = NavHost(
    bottomNavController,
    items.firstOrNull()?.route.orEmpty(),
) {
    items.forEach { page ->
        composable(page.route, page.arguments) {
            page.content(navController, bundleOf(*args))
        }
    }
}

@Composable
fun rememberAdmin() = remember { ClientStorage.get<Admin>() as? AdminClient }

@Composable
fun rememberCustomer() = remember { ClientStorage.get<Customer>() as? CustomerClient }

fun Long.toDate() = Date(this)

fun String.withArgs(vararg args: Pair<String, Any?>): String {
    return args.fold(this) { path, (key, value) -> path.replace("{$key}", value.toString()) }
}