package com.ordersspace.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.TableRestaurant
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.android.animation.SegmentBreaker
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Meню") })
        },
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                ChoosingPlace(

                )
            }
        }
    }

}

@Composable
fun ChoosingPlace() {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    )
    {
        Column() {
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(Icons.Outlined.Restaurant, null)
                }
                Button(onClick = { /*TODO*/ }) {
                    Icon(Icons.Outlined.TableRestaurant, null)
                }
                Button(onClick = { /*TODO*/ }) {
                    Icon(Icons.Outlined.ShoppingBag, null)
                }
            }
        }
    }
}

class XelaSegmentedControlItem(i: Int, s: String) {

}





