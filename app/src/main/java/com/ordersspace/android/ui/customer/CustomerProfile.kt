package com.ordersspace.android.ui.customer

import android.annotation.SuppressLint
import android.graphics.drawable.DrawableContainer.DrawableContainerState
import android.widget.Button
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SocialDistance
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.R
import com.ordersspace.android.model.User
import com.ordersspace.android.ui.NavigationPage.AdminMenuPage.title
import com.ordersspace.android.ui.admin.NetworkCard
import com.ordersspace.android.ui.navigation.AdminRoutes.networks
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import kotlinx.serialization.json.JsonNull.content
import okhttp3.internal.userAgent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomerProfile(navController: NavHostController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "Профиль"
                )
            })
        },
    )

    {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {

            var name by remember { mutableStateOf("") }
            OutlinedTextField(
                label = { Text(text = "Имя") },
                value = name,
                onValueChange = { name = it },
            )
            var surname by remember { mutableStateOf("") }
            OutlinedTextField(
                label = { Text(text = "Фамилия") },
                value = surname,
                onValueChange = { surname = it },
            )
            var date by remember { mutableStateOf("") }
            OutlinedTextField(
                label = { Text(text = "Дата рождения") },
                value = date,
                onValueChange = { date = it },
            )
            var gender by remember { mutableStateOf("") }
            OutlinedTextField(
                label = { Text(text = "Пол") },
                value = gender,
                onValueChange = { gender = it },
            )
            var phone by remember { mutableStateOf("") }
            OutlinedTextField(
                label = { Text(text = "Телефон") },
                value = phone,
                onValueChange = { phone = it },
            )
            var email by remember { mutableStateOf("") }
            OutlinedTextField(
                label = { Text(text = "Эл. почта") },
                value = email,
                onValueChange = { email = it },
            )
            TextButton(onClick = { /*TODO*/ }) {
                Text("Выйти из профиля",color = Color.Red)
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text("Удалить профиль", color = Color.Red)
            }
        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun CustomerProfilePreview() {
    OrdersSpaceTheme {
        CustomerProfile(rememberNavController())
    }
}
