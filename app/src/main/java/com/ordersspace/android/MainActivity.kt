package com.ordersspace.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.ui.*
import com.ordersspace.android.ui.theme.OrdersSpaceTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OrdersSpaceTheme {
                val controller = rememberNavController()
                NavHost(
                    navController = controller,
                    startDestination = AdminMenuPage.route,
                ) {
                    composable(route = LoginPage.route) {
                        LoginPage(controller)
                    }
                    composable(route = MainPage.route) {
                        MainPage()
                    }
                    composable(route = AuthPage.route) {
                        AuthPage(controller)
                    }
                    composable(route = AdminPage.route) {
                        AdminPage(controller)
                    }
                    composable(route = AdminMenuPage.route){
                        AdminMenuPage(controller)
                    }
                    composable(route = SettingsItemPage.route){
                        SettingsItemPage(controller)
                    }
                }
            }
        }
    }
}


