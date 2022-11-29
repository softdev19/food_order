package com.ordersspace.android.ui.customer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.ui.theme.OrdersSpaceTheme

@Composable
fun PlaceMap(navController: NavHostController) {
    Text("PlaceMap")
}

@Preview(showSystemUi = true)
@Composable
fun PlaceMapPreview() {
    OrdersSpaceTheme {
        PlaceMap(rememberNavController())
    }
}