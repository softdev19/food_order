package com.ordersspace.android.ui

import android.annotation.SuppressLint
import android.text.style.BackgroundColorSpan
import android.widget.Button
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.Start
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.material3.*
import androidx.compose.material3.IconButtonDefaults.iconButtonColors
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminPage(controller: NavController? = null) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Информация о ресторане") })
    }, bottomBar = {
        BottomAppBar(modifier = Modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(155.dp)
                )
                Icon(Icons.Filled.RestaurantMenu, null)
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(20.dp)
                )
                Icon(Icons.Filled.ListAlt, null)
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(155.dp)
                )
            }

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
            Row(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.SpaceAround,
            ) {
                OutlinedTextField(
                    label = { Text(text = "Местоположение") },
                    value = location,
                    onValueChange = { location = it },
                )

            }

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

object AdminPage {
    const val route = "/login"
}