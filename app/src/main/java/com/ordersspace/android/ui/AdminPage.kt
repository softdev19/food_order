package com.ordersspace.android.ui

import android.annotation.SuppressLint
import android.graphics.Outline
import android.text.style.BackgroundColorSpan
import android.widget.Button
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.Start
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.material3.IconButtonDefaults.iconButtonColors
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ordersspace.android.R
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import io.ktor.http.*
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Preview(showSystemUi = true, name = "Admin page")
@Composable
fun AdminPagePreview() {
    OrdersSpaceTheme {
        AdminPage()
    }
}
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminPage(controller: NavController? = null) {
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

object AdminPage {
    const val route = "/admin"
}