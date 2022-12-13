package com.ordersspace.android.ui

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.ordersspace.android.ui.theme.OrdersSpaceTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Basket(controller: NavHostController? = null) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { controller?.popBackStack() }) {
                        Icon(Icons.Outlined.ArrowBack, "Назад")
                    }
                },
                title = { Text("Корзина") },
                actions = {

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Outlined.Delete, "Очистить")
                    }
                },
            )
        }
    )
    {

    }
}
@Preview(showSystemUi = true)
@Composable
fun BasketPreview() {
    OrdersSpaceTheme {
        Basket()
    }
}