package com.ordersspace.android.ui

import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ordersspace.android.ui.theme.OrdersSpaceTheme

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
    Text("Menu")
}



