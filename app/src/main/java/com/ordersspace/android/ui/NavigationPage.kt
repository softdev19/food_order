package com.ordersspace.android.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Store
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationPage(
    val icon: ImageVector,
    val title: String,
    val route: String,
) {
    object MapPage : NavigationPage(Icons.Outlined.Store, "Рестораны", "/map")
    object MenuPage : NavigationPage(Icons.Outlined.MenuBook, "Меню", "/menu")
    object ProfilePage : NavigationPage(Icons.Outlined.AccountCircle, "Профиль", "/profile")
}
