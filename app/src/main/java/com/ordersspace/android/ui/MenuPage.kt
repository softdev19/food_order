package com.ordersspace.android.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ordersspace.android.model.Client
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import kotlinx.coroutines.runBlocking

@Preview(showSystemUi = true, name = "Menu page")
@Composable
fun MenuPagePreview() {
    OrdersSpaceTheme {
        MenuPage()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuPage() {

}

object MenuPage {
    const val route = "/menu"
}
