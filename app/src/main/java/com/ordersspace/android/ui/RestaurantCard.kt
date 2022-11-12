package com.ordersspace.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ordersspace.android.R
import com.ordersspace.android.model.Place

@Composable
fun RestaurantCard( place : Place) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(text = place.name)
        Image(
            painter = painterResource(id = R.drawable.logot),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(0.45f)
        )
        Text(text = place.description)
    }
}

