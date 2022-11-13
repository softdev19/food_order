package com.ordersspace.android.ui

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ordersspace.android.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthPage(navController: NavController) {
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
            onValueChange = { name = it },)
        OutlinedTextField(
            label = { Text(text = "Пароль") },
            value = name,
            onValueChange = { name = it },)

        Button(
            onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(), enabled = true
        ) {
            Text(text = "Зарегистрироваться")
        }
    }
}


object AuthPage {
    const val route = "/auth"
}