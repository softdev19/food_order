package com.ordersspace.android.ui.navigation

import android.os.Bundle
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ordersspace.android.model.Admin
import com.ordersspace.android.ui.admin.PlacesPage
import com.ordersspace.android.ui.customer.*
import com.ordersspace.android.ui.admin.*
import com.ordersspace.android.ui.common.SignupPage
import com.ordersspace.android.ui.common.LoginPage

object CommonRoutes {

    const val login = "/login"
    const val signup = "/signup"
}

object CustomerRoutes {

    const val main = "/customer"

    const val map = "$main/map"

    const val menu = "$main/menu"
    const val place = "$menu/place"
    const val item = "$menu/item"
    const val cart = "$menu/cart"
    const val checkout = "$menu/checkout"

    const val profile = "$main/profile"
    const val status = "$profile/status"
    const val history = "$profile/history"
}

object EmployeeRoutes {

    const val main = "/employee"

    const val shift = "$main/shift"

    const val orders = "$main/orders"
    const val status = "$orders/status"
}

typealias RoutePageWithArgs = @Composable (controller: NavHostController, args: Bundle) -> Unit
typealias RoutePage = @Composable (controller: NavHostController) -> Unit

object AdminRoutes {

    const val main = "/admin"
    const val networks = "$main/networks"
    const val network = "$networks/{id}"
    const val editNetwork = "$network/edit"
    const val places = "$network/places"
    const val place = "$places/{pid}"
    const val editPlace = "$place/edit"
    const val menu = "$network/menu"
    const val editItem = "$menu/{iid}/edit"
}

sealed class Route {

    data class ScreenRoute(
        val route: String,
        val arguments: List<NamedNavArgument> = emptyList(),
        val content: RoutePageWithArgs,
    ) : Route()

    data class ScreenPartRoute(
        val route: String,
        val name: String,
        val icon: ImageVector,
        val arguments: List<NamedNavArgument> = emptyList(),
        val content: RoutePageWithArgs,
    ) : Route()

    companion object {

        private inline fun ScreenRoute(
            route: String,
            crossinline content: RoutePage,
        ) = ScreenRoute(route) { controller, _ -> content(controller) }

        private inline fun ScreenPartRoute(
            route: String,
            name: String,
            icon: ImageVector,
            crossinline content: RoutePage,
        ) = ScreenPartRoute(route, name, icon) { controller, _ -> content(controller) }

        val screenRoutes = listOf(
            // Common routes
            ScreenRoute(CommonRoutes.login) { controller ->
                LoginPage(
                    onLoginSuccess = { user -> controller.navigate(if (user is Admin) AdminRoutes.main else CustomerRoutes.main) },
                    onSignupClick = { controller.navigate(CommonRoutes.signup) },
                )
            },
            ScreenRoute(CommonRoutes.signup) {
                SignupPage(
                    onSignupSuccess = { user -> it.navigate(if (user is Admin) AdminRoutes.main else CustomerRoutes.main) },
                )
            },

            // Customer routes
            ScreenRoute(CustomerRoutes.main) { CustomerMain(it) },
            ScreenRoute(CustomerRoutes.cart) { CartPage(it) },
            ScreenRoute(CustomerRoutes.place) { /* TODO: place page */ },
            ScreenRoute(CustomerRoutes.item) { /* TODO: item description page */ },
            ScreenRoute(CustomerRoutes.checkout) { /* TODO: checkout page */ },
            ScreenRoute(CustomerRoutes.status) { /* TODO: order status page */ },

            // Admin routes
            ScreenRoute(AdminRoutes.main) { AdminMain(it) },
            ScreenRoute(
                AdminRoutes.network,
                listOf(navArgument("id") { type = NavType.LongType }),
            ) { controller, args -> NetworkPage(controller, args.getLong("id", -1L)) },
            ScreenRoute(
                AdminRoutes.place,
                listOf(
                    navArgument("id") { type = NavType.LongType },
                    navArgument("pid") { type = NavType.LongType },
                ),
            ) { controller, args ->
                PlacePage(controller, args.getLong("id", -1L), args.getLong("pid", -1L))
            },
            ScreenRoute(
                AdminRoutes.editItem,
                listOf(
                    navArgument("id") { type = NavType.LongType },
                    navArgument("iid") { type = NavType.LongType },
                ),
            ) { controller, args ->
                MenuItemPage(controller, args.getLong("id", -1L), args.getLong("iid", -1L))
            }
        )

        val customerMainPages = listOf(
            ScreenPartRoute(CustomerRoutes.map, "Рестораны", Outlined.Store) { PlaceMap(it) },
            ScreenPartRoute(CustomerRoutes.menu, "Меню", Outlined.MenuBook) { MenuPage(it) },
            ScreenPartRoute(CustomerRoutes.profile, "Профиль", Outlined.AccountCircle) { CustomerProfile(it::navigate) },
        )

        val customerProfilePages = listOf(
            ScreenPartRoute(CustomerRoutes.history, "История заказов", Outlined.History) { HistoryPage() },
        )

        val adminMainPages = listOf(
            ScreenPartRoute(AdminRoutes.networks, "Сети", Outlined.Storefront) { NetworksPage(it) }
        )

        val networkPagePages = listOf(
            ScreenPartRoute(
                AdminRoutes.editNetwork,
                "Информация",
                Outlined.EditNote,
                listOf(navArgument("id") { type = NavType.LongType }),
            ) { controller, args ->
                NetworkEditPage(controller, args.getLong("id", -1L))
            },
            ScreenPartRoute(
                AdminRoutes.places,
                "Рестораны",
                Outlined.Restaurant,
                listOf(navArgument("id") { type = NavType.LongType }),
            ) { controller, args ->
                PlacesPage(controller, args.getLong("id", -1L))
            },
            ScreenPartRoute(
                AdminRoutes.menu,
                "Меню",
                Outlined.MenuBook,
                listOf(navArgument("id") { type = NavType.LongType }),
            ) { controller, args ->
                NetworkMenuPage(controller, args.getLong("id", -1L))
            }
        )

        val placePagePages = listOf(
            ScreenPartRoute(
                AdminRoutes.editPlace,
                "Информация",
                Outlined.EditNote,
                listOf(
                    navArgument("id") { type = NavType.LongType },
                    navArgument("pid") { type = NavType.LongType }
                ),
            ) { controller, args ->
                PlaceEditPage(controller, args.getLong("id", -1L), args.getLong("pid", -1L))
            }
        )
    }
}