package com.ordersspace.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ordersspace.android.R
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview(showSystemUi = true, name = "Login page")
@Composable
fun LoginPagePreview() {
    OrdersSpaceTheme {
        LoginPage()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginPage(controller: NavController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logot),
            contentDescription = "Orders Space",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1.0f)
        )
        Box(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

        }

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {

        }

        var phone by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }

        OutlinedTextField(
            label = { Text(text = "Логин") },
            value = name,
            onValueChange = { name = it },
        )
        OutlinedTextField(
            label = { Text(text = "Пароль") },
            value = phone,
            onValueChange = { phone = it },
        )
        Button(
            onClick = {
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Войти")
        }

        Button(
            onClick = {
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Регистрация")
        }
    }
}


fun login(
    name: String,
    phone: String?,
    email: String?,
    scope: CoroutineScope,
    controller: NavController,
) {
    scope.launch {
//        Client.addUser(name, phone, email)
        controller.navigate(MainPage.route)
    }
}

object LoginPage {
    const val route = "/login"
}
