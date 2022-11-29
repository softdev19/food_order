package com.ordersspace.android.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Store
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.ordersspace.android.ui.customer.CustomerMain
import com.ordersspace.android.ui.customer.CustomerProfile
import com.ordersspace.android.ui.customer.MainMenu
import com.ordersspace.android.ui.customer.PlaceMap

data class Page(
    val route: String,
    val label: String = "",
    val icon: ImageVector = Icons.Outlined.Add,
    val content: @Composable (NavHostController) -> Unit,
) {

    companion object {

        val PlaceMap = Page(
            CustomerRoutes.map,
            "Рестораны",
            Icons.Outlined.Store,
        ) { PlaceMap(it) }

        val MainMenu = Page(
            CustomerRoutes.menu,
            "Меню",
            Icons.Outlined.MenuBook,
        ) { MainMenu(it) }

        val CustomerProfile = Page(
            CustomerRoutes.profile,
            "Профиль",
            Icons.Outlined.AccountCircle,
        ) { CustomerProfile(it) }

        val CustomerMain = Page(CustomerRoutes.main) { CustomerMain(it) }
    }
}
