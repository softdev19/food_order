package com.ordersspace.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ordersspace.android.ui.*
import com.ordersspace.android.ui.admin.NetworkEditPage
import com.ordersspace.android.ui.admin.NetworksPage
import com.ordersspace.android.ui.customer.CustomerMain
import com.ordersspace.android.ui.navigation.AdminRoutes
import com.ordersspace.android.ui.navigation.CommonRoutes
import com.ordersspace.android.ui.navigation.CustomerRoutes
import com.ordersspace.android.ui.theme.OrdersSpaceTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OrdersSpaceTheme {
                val controller = rememberNavController()
                NavHost(
                    navController = controller,
                    startDestination = CommonRoutes.login
                ) {
                    composable(route = CustomerRoutes.main) {
                        CustomerMain(controller)
                    }
                    composable(route = MainPage.route) {
                        MainPage()
                    }
                    composable(route = AdminMenuPage.route) {
                        AdminMenuPage(controller)
                    }
                    composable(route = SettingsItemPage.route) {
                        SettingsItemPage(controller)
                    }
                    composable(
                        route = AdminRoutes.network,
                        arguments = listOf(navArgument("id", { NavType.LongType }))
                    ) {
                        NetworkEditPage(controller, it.arguments?.getLong("id"))
                    }
                    composable(route = AdminRoutes.networks) {
                        NetworksPage(controller)
                    }
                    composable(route = CommonRoutes.login) {
                        LoginPage(controller)
                    }
                    composable(route = CommonRoutes.signup) {
                       AuthPage (controller)
                    }
                    composable(route = CustomerRoutes.cart) {
                        Basket(controller)
                    }
                }
            }
        }
    }
}


