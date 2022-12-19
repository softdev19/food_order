package com.ordersspace.android.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ordersspace.android.R
import com.ordersspace.android.client.*
import com.ordersspace.android.client.Client.Companion.isValidEmail
import com.ordersspace.android.client.Client.Companion.isValidName
import com.ordersspace.android.client.Client.Companion.isValidPassword
import com.ordersspace.android.client.Client.Companion.isValidPhone
import com.ordersspace.android.model.User
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupPage(
    onSignupSuccess: (User) -> Unit = {},
) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    fun signup() = signup(
        name, password,
        phone.takeIf { it.isNotEmpty() && it.isValidPhone },
        email.takeIf { it.isNotEmpty() && it.isValidEmail },
        scope,
        clientFactory = ::CustomerClient,
        onSignupSuccess,
        onSignupFailure = {  },
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    signup(
                        name, password,
                        phone.takeIf { it.isNotEmpty() && it.isValidPhone },
                        email.takeIf { it.isNotEmpty() && it.isValidEmail },
                        scope,
                        clientFactory = ::AdminClient,
                        onSignupSuccess,
                        onSignupFailure = {  },
                    )
                },
            ) {
                Icon(Icons.Outlined.AdminPanelSettings, "Регистрация администратора")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
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
            OutlinedTextField(
                label = { Text(text = "Логин") },
                value = name,
                onValueChange = { name = it },
                isError = !name.isValidName,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            )
            OutlinedTextField(
                label = { Text(text = "Пароль") },
                value = password,
                onValueChange = { password = it },
                isError = !password.isValidPassword,
                supportingText = { Text("Пароль должен состоять из не менее 8 символов, содержать заглавную и строчную латинские буквы, цифру и символ") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            )
            OutlinedTextField(
                label = { Text(text = "Телефон") },
                value = phone,
                onValueChange = { phone = it },
                isError = !phone.isValidPhone,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            )
            OutlinedTextField(
                label = { Text(text = "Почта") },
                value = email,
                onValueChange = { email = it },
                isError = !email.isValidEmail,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { signup() })
            )
            Box(modifier = Modifier.height(32.dp))
            Button(
                onClick = { signup() },
                modifier = Modifier.fillMaxWidth(), enabled = true
            ) {
                Text(text = "Зарегистрироваться")
            }
        }
    }
}

private inline fun <reified U : User> signup(
    name: String,
    password: String,
    phone: String?,
    email: String?,
    scope: CoroutineScope,
    crossinline clientFactory: (name: String, password: String) -> Client<U>,
    noinline onSignupSuccess: (U) -> Unit,
    noinline onSignupFailure: () -> Unit,
) {
    scope.launch {
        val client = clientFactory(name, password)
        val user = client.signup(phone, email)
            ?: return@launch onSignupFailure()
        ClientStorage.put(client)
        onSignupSuccess(user)
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun SignupPagePreview() = OrdersSpaceTheme { SignupPage() }