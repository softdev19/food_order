package com.ordersspace.android.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ordersspace.android.R
import com.ordersspace.android.client.AdminClient
import com.ordersspace.android.client.ClientStorage
import com.ordersspace.android.client.CustomerClient
import com.ordersspace.android.client.CustomerStorage
import com.ordersspace.android.ui.navigation.AdminRoutes
import com.ordersspace.android.ui.navigation.CustomerRoutes
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview(showSystemUi = true)
@Composable
fun AuthPagePreview() {
    OrdersSpaceTheme() {
        AuthPage()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthPage(navController: NavController? = null) {
    val scope = rememberCoroutineScope()
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
        var name by remember { mutableStateOf("") }
        OutlinedTextField(
            label = { Text(text = "Логин") },
            value = name,
            onValueChange = { name = it },
        )
        var password by remember { mutableStateOf("") }
        OutlinedTextField(
            label = { Text(text = "Пароль") },
            value = password,
            onValueChange = { password = it },
        )
        var phone by remember { mutableStateOf("") }
        OutlinedTextField(
            label = { Text(text = "Телефон") },
            value = phone,
            onValueChange = { phone = it },
        )
        var email by remember { mutableStateOf("") }
        OutlinedTextField(
            label = { Text(text = "Почта") },
            value = email,
            onValueChange = { email = it },
        )


        Button(
            onClick = { if (navController != null) {
                customerAuth(
                    name,
                    password,
                    phone.takeIf { it.isNotEmpty() },
                    email.takeIf { it.isNotEmpty() },
                    scope,
                    navController,
                )
            } }, modifier = Modifier.fillMaxWidth(), enabled = true
        ) {
            Text(text = "Зарегистрироваться")
        }
        Button(
            onClick = {
                if (navController != null) {
                    adminAuth(
                        name,
                        password,
                        phone.takeIf { it.isNotEmpty() },
                        email.takeIf { it.isNotEmpty() },
                        scope,
                        navController,
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true
        ) {
            Text(text = "Зарегистрироваться как администратор")
        }
    }
}

fun adminAuth(
    name: String,
    password: String,
    phone: String?,
    email: String?,
    scope: CoroutineScope,
    controller: NavController,
) {
    scope.launch {
        val adminClient = AdminClient(name, password)
        ClientStorage.admin = adminClient
        Log.d("OS","Registering admin")
        val admin = adminClient.signup(phone, email) ?: return@launch
        Log.d("OS","Registered admin")
        controller.navigate(AdminRoutes.networks)
    }
}
fun customerAuth(
    name: String,
    password: String,
    phone: String?,
    email: String?,
    scope: CoroutineScope,
    controller: NavController,
) {
    scope.launch {
        val customerClient = CustomerClient(name, password)
        CustomerStorage.customer = customerClient
        Log.d("OS","Registering customer")
        val custromer = customerClient.signup(phone, email) ?: return@launch
        Log.d("OS","Registered custromer")
        controller.navigate(CustomerRoutes.main)
    }
}

