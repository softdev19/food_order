package com.ordersspace.android.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ordersspace.android.R
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import com.ordersspace.android.ui.theme.PurpleGrey40

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
fun LoginPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val scale by remember { mutableStateOf(1f) }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Orders Space",
            modifier = Modifier.
            fillMaxWidth(0.5f).
            aspectRatio(1.0f)
        )
        Box(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { /*TODO*/ }, modifier = Modifier.weight(1.0f)
            ) {
                Text(text = "Google")
            }
            Box(modifier = Modifier.width(16.dp))
            Button(
                onClick = { /*TODO*/ }, modifier = Modifier.weight(1.0f)
            ) {
                Text(text = "Вконтакте")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(1.5.dp)
                    .weight(1.0f)
                    .background(Color.Black)
            )

            Text(
                "или", modifier = Modifier.padding(horizontal = 8.dp)
            )

            Box(
                modifier = Modifier
                    .height(1.5.dp)
                    .weight(1.0f)
                    .background(Color.Black)
            )
        }
        var numb by remember { mutableStateOf("") }
        var code by remember { mutableStateOf("") }
        OutlinedTextField(
            label = { Text(text = "Номер телефона") },
            value = numb,
            onValueChange = { numb = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            )
        )
        var isVisible by remember {
            mutableStateOf(false)
        }

        AnimatedVisibility(!isVisible) {
            Button(
                onClick = {
                    isVisible = true
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Получить код в СМС")
            }
        }
        AnimatedVisibility(isVisible) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    label = { Text(text = "Код из СМС") },
                    value = code,
                    onValueChange = { code = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    )
                )
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Войти")
                }
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Получить СМС еще раз")
                }
            }

        }

    }
}

object LoginPage {
    const val route = "/login"
}
