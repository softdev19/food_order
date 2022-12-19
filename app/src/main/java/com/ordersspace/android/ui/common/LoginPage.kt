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
import com.ordersspace.android.model.User
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@Preview(showSystemUi = true, name = "Login page")
fun LoginPagePreview() = OrdersSpaceTheme { LoginPage() }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    onLoginSuccess: (User) -> Unit = {},
    onSignupClick: () -> Unit = {},
) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    fun login() = login(name, password, scope, ::AdminClient, onLoginSuccess) { isError = true }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { login() }) {
                Icon(Icons.Outlined.AdminPanelSettings, "Вход для администраторов")
            }
        },
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
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text("Имя") },
                singleLine = true,
                value = name,
                onValueChange = { name = it; isError = false },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            )
            OutlinedTextField(
                label = { Text("Пароль") },
                singleLine = true,
                value = password,
                onValueChange = { password = it; isError = false },
                isError = isError,
                supportingText = { if (isError) Text("Не получилось войти") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { login() })
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    login(
                        name, password, scope,
                        clientFactory = ::CustomerClient,
                        onLoginSuccess,
                        onLoginFailure = { isError = true },
                    )
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text("Войти")
            }
            TextButton(onClick = onSignupClick) {
                Text("Регистрация")
            }
        }
    }
}

private inline fun <reified U : User> login(
    name: String,
    password: String,
    scope: CoroutineScope,
    crossinline clientFactory: (name: String, password: String) -> Client<U>,
    noinline onLoginSuccess: (U) -> Unit,
    noinline onLoginFailure: () -> Unit,
) {
    scope.launch {
        val client = clientFactory(name, password)
        val user = client.authenticate()
            ?: return@launch onLoginFailure()
        ClientStorage.put(client)
        onLoginSuccess(user)
    }
}