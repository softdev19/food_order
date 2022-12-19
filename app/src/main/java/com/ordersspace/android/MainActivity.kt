package com.ordersspace.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.core.os.bundleOf
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ordersspace.android.ui.*
import com.ordersspace.android.ui.admin.NetworkEditPage
import com.ordersspace.android.ui.admin.NetworksPage
import com.ordersspace.android.ui.customer.CustomerMain
import com.ordersspace.android.ui.navigation.AdminRoutes
import com.ordersspace.android.ui.navigation.CommonRoutes
import com.ordersspace.android.ui.navigation.CustomerRoutes
import com.ordersspace.android.ui.navigation.Route
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
                    Route.screenRoutes.forEach { route ->
                        composable(route.route, route.arguments) {
                            route.content(controller, it.arguments ?: bundleOf())
                        }
                    }
                }
            }
        }
    }
}


