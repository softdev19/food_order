package com.ordersspace.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.ui.theme.Item
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import io.ktor.http.*

@Preview(showSystemUi = true, name = "SettingsItemPage")
@Composable
fun SettingsItemPagePreview() {
    OrdersSpaceTheme {
        SettingsItemPage()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsItemPage(controller: NavController? = null) {
    Scaffold(topBar = {
        val navController = rememberNavController()
        CenterAlignedTopAppBar(title = { Text(text = "Настройка блюда") })
    }, bottomBar = {
        BottomAppBar(modifier = Modifier.fillMaxWidth()) {

            NavigationBarItem(
                icon = {
                },
                selected = false,
                onClick = {},
                label = {
                    Text(
                        text = "Добавить",
                        fontSize = (23.sp),
                        modifier = Modifier.padding(10.dp)
                    )
                })
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var title by remember { mutableStateOf("") }
            var description by remember { mutableStateOf("") }
            var weight by remember { mutableStateOf("") }
            var kcal by remember { mutableStateOf("") }
            var price by remember { mutableStateOf("") }
            var stock by remember { mutableStateOf("") }
            OutlinedTextField(
                label = { Text(text = "Название") },
                value = title,
                onValueChange = { title = it },
            )
            OutlinedTextField(
                label = { Text(text = "Описание") },
                value = description,
                onValueChange = { description = it },
            )
            OutlinedTextField(
                label = { Text(text = "Масса") },
                value = weight,
                onValueChange = { weight = it },
            )
            OutlinedTextField(
                label = { Text(text = "Ккал") },
                value = kcal,
                onValueChange = { kcal = it },
            )
            OutlinedTextField(
                label = { Text(text = "Цена") },
                value = price,
                onValueChange = { price = it },
            )
            OutlinedTextField(
                label = { Text(text = "Акционная цена") },
                value = stock,
                onValueChange = { stock = it },
            )
        }
    }
}

object SettingsItemPage {
    const val route = "settings/item"
}