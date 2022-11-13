package com.ordersspace.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.ui.theme.OrdersSpaceTheme

@Composable
@Preview(showSystemUi = true)
fun MainPagePreview() {
    OrdersSpaceTheme {
        MainPage()
    }
}

val pages = listOf(
    NavigationPage.MapPage,
    NavigationPage.MenuPage,
    NavigationPage.ProfilePage,
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {
    val navController = rememberNavController()
    Scaffold(

        bottomBar = { BottomNavigation(navController) },
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Navigation(navController)
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    BottomAppBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val route = backStackEntry?.destination?.route
        pages.forEach {
            NavigationBarItem(
                icon = { Icon(it.icon, null) },
                label = { Text(it.title) },
                selected = route == it.route,
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
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationPage.MenuPage.route,
    ) {
        composable(NavigationPage.MapPage.route) {
            MapPage()
        }
        composable(NavigationPage.MenuPage.route) {
            MenuPage()
        }
        composable(NavigationPage.ProfilePage.route) {
            ProfilePage()
        }
    }
}

object MainPage {
    const val route = "/main"
}