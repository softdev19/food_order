package com.ordersspace.android.ui

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.ordersspace.android.R
import com.ordersspace.android.model.Client
import com.ordersspace.android.model.User
import com.ordersspace.android.ui.MenuPage.route
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import kotlinx.coroutines.runBlocking

@Preview(showSystemUi = true, name = "Menu page")
@Composable
fun MenuPagePreview() {
    OrdersSpaceTheme {
        MenuPage()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuPage() {
    Scaffold(topBar = {
        Text(text = "Меню")
    }, bottomBar = {

    }) {

    }
}

object MenuPage {
    const val route = "/menu"
}

@Composable
fun BottomNavigationBar() {

}



@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}