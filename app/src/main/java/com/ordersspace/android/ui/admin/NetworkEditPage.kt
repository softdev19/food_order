package com.ordersspace.android.ui.admin

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
import com.ordersspace.android.client.ClientStorage
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import io.ktor.http.*
import kotlinx.coroutines.runBlocking

@Preview(showSystemUi = true, name = "Admin page")
@Composable
fun NetworkEditPagePreview() {
    OrdersSpaceTheme {
        NetworkEditPage()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NetworkEditPage(controller: NavController? = null,networkId : Long? = null) {
    val admin = ClientStorage.admin
    val network = networkId?.let {  runBlocking { admin?.getNetwork(it.toULong()) }}
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Информация о ресторане") })
    }, bottomBar = {
        BottomAppBar(modifier = Modifier.fillMaxWidth()) {

            NavigationBarItem(icon = {
                Icon(
                    Icons.Filled.RestaurantMenu, contentDescription = "Favorite"
                )

            }, selected = false, onClick = {}, label = {Text(text = "Информация")})
            NavigationBarItem(icon = {
                Icon(
                    Icons.Filled.ListAlt, contentDescription = "Favorite"
                )

            }, selected = false, onClick = {},label = {Text(text = "Меню")})


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
            var location by remember { mutableStateOf("") }
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
                label = { Text(text = "Местоположение") },
                value = location,
                onValueChange = { location = it },
            )

            Box(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
                    .background(color = Color.White)
            )
            Box(
                modifier = Modifier
                    .height(0.5.dp)
                    .fillMaxWidth()
                    .background(color = Color.Gray)
            )
            var phone by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var vk by remember { mutableStateOf("") }
            OutlinedTextField(
                label = { Text(text = "Телефон") },
                value = phone,
                onValueChange = { phone = it },
            )
            OutlinedTextField(
                label = { Text(text = "Электронная почта") },
                value = email,
                onValueChange = { email = it },
            )
            OutlinedTextField(
                label = { Text(text = "VK") },
                value = vk,
                onValueChange = { vk = it },
            )

            Divider()


            var docs by remember { mutableStateOf("") }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Filled.Upload, null)
                Text("Загрузить Документы")
            }


            Box(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
                    .background(color = Color.White)
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ },

                ) {
                Text(
                    "Отправить на проверку",
                    fontSize = 20.sp,
                )

            }

        }
    }
}

@Composable
fun Divider() {
    Box(
        modifier = Modifier
            .height(10.dp)
            .fillMaxWidth()
            .background(color = Color.White)
    )
    Box(
        modifier = Modifier
            .height(0.5.dp)
            .fillMaxWidth()
            .background(color = Color.Gray)
    )
    Box(
        modifier = Modifier
            .height(10.dp)
            .fillMaxWidth()
            .background(color = Color.White)
    )
}

object NetworkEdditPage {
    const val route = "/admin"
}