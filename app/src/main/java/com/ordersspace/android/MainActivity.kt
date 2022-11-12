package com.ordersspace.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.datastore.core.DataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.ui.LoginPage
import com.ordersspace.android.ui.MenuPage
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import java.util.prefs.Preferences

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OrdersSpaceTheme {
                val controller = rememberNavController()
                NavHost(
                    navController = controller,
                    startDestination = LoginPage.route,
                ) {
                    composable(route = MenuPage.route) {
                        MenuPage()
                    }
                    composable(route = LoginPage.route) {
                        LoginPage(controller)
                    }
                }
            }
        }
    }
}
