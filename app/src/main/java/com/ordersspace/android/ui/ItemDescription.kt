package com.ordersspace.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ordersspace.android.model.MenuItem
import com.ordersspace.android.ui.navigation.CustomerRoutes.item
import com.ordersspace.android.ui.theme.OrdersSpaceTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ItemDescription(item: MenuItem) {
    Scaffold(
        bottomBar = {
        BottomAppBar() {
            Column() {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = item.name)
                    Text(text = item.weight.toString())
                    Text(text = item.cost.toString())
                    Card() {
                        var counter by remember { mutableStateOf(1) }
                        Row() {
                            IconButton(onClick = { counter++ }) {
                                Icon(Icons.Outlined.Add, "Добавить")
                            }
                            Text(text = "$counter")
                            IconButton(onClick = { counter++ }) {
                                Icon(Icons.Outlined.Remove, "Удалить")
                            }
                        }
                    }
                }
            }
        }
    }){
        Column() {
            Text(text = item.description.orEmpty())
            Text(item.name.orEmpty())
            Text("Выберите до 10")
        }


    }
}


@Preview(showSystemUi = true)
@Composable
fun ItemDescriptionPreview() {
    OrdersSpaceTheme {
        val item = MenuItem(
            id = 0UL,
            name = "gurber",
            type = MenuItem.ItemType.DISH,
            cost = 69.99,
            weight = 69.99,
            volume = 69.99,
            description = "best food ever",
            isAgeRestricted = false,
            imageUrl = "https://media.discordapp.net/attachments/886662838322614364/1049644538500747314/image.png",
            networkId = 0UL,
        )
        ItemDescription(item = item)
    }
}


