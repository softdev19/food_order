@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.admin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.ui.BottomAppBar
import com.ordersspace.android.ui.BottomNavContent
import com.ordersspace.android.ui.navigation.Route

@Composable
fun PlacePage(navController: NavHostController, networkId: Long, placeId: Long) {
    val bottomNavController = rememberNavController()
    val pages = remember { Route.placePagePages }
    val id = "id" to networkId
    val pid = "pid" to placeId

    Scaffold(
        bottomBar = { BottomAppBar(pages, bottomNavController, id, pid) },
    ) {
        Box(Modifier.padding(it)) {
            BottomNavContent(pages, navController, bottomNavController, id, pid)
        }
    }
}
