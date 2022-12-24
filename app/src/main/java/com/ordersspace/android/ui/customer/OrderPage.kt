@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.customer

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ordersspace.android.ui.navigation.CustomerRoutes
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun OrderPage(controller: NavHostController) {
    val view = LocalView.current
    val window = remember { (view.context as? Activity)?.window }
    val colorScheme = MaterialTheme.colorScheme
    val number = remember {
        "${Random.nextInt(0x0410..0x042F).toChar()}-${Random.nextInt(0..99).toString().padStart(2, '0')}"
    }

    SideEffect {
        window?.statusBarColor = colorScheme.surfaceColorAtElevation(2.dp).toArgb()
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp))
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Номер вашего заказа")
        Text(number, style = MaterialTheme.typography.displayLarge)
        Text("Мы уведомим вас,\n когда он будет готов", textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        Button(onClick = { controller.popBackStack(CustomerRoutes.main, false) }) {
            Text("Назад в меню")
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun OrderPagePreview() = OrdersSpaceTheme { OrderPage(rememberNavController()) }