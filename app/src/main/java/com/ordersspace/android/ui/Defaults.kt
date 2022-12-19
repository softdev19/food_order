package com.ordersspace.android.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable

object Defaults {

    @Composable
    fun BackButton(onClick: () -> Unit) {
        IconButton(onClick) {
            Icon(Icons.Outlined.ArrowBack, "Назад")
        }
    }
}
