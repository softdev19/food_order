package com.ordersspace.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {
    var selectedIndex by remember {
        mutableStateOf(1)
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Meню") })
    }, bottomBar = {
        BottomAppBar(
        ) {
            NavigationBarItem(
                selected = selectedIndex == 0,
                onClick = { selectedIndex = 0 },
                icon = { Icon(Icons.Default.Store, "") },
                label = { Text("Рестораны") },
            )
            NavigationBarItem(
                selected = selectedIndex == 1,
                onClick = { selectedIndex = 1 },
                icon = { Icon(Icons.Default.MenuBook, "") },
                label = { Text("Меню") },
            )
            NavigationBarItem(
                selected = selectedIndex == 2,
                onClick = { selectedIndex = 2 },
                icon = { Icon(Icons.Default.AccountCircle, "") },
                label = { Text("Профиль") },
            )
        }
    })
    {

    }
}

object MainPage {
    const val route = "/main"
}