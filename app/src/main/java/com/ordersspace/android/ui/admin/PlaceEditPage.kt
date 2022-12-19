@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ordersspace.android.client.AdminClient
import com.ordersspace.android.client.ClientStorage
import com.ordersspace.android.model.Admin
import com.ordersspace.android.model.Place
import com.ordersspace.android.ui.Defaults
import com.ordersspace.android.ui.navigation.AdminRoutes
import com.ordersspace.android.ui.rememberAdmin
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import com.ordersspace.android.ui.withArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PlaceEditPage(
    controller: NavController,
    networkId: Long = -1L,
    placeId: Long = -1L,
) {
    val scope = rememberCoroutineScope()
    val admin = rememberAdmin()
    var place: Place? by remember { mutableStateOf(null) }
    var name: String? by remember { mutableStateOf(null) }
    var description: String? by remember { mutableStateOf(null) }
    var imageUrl: String? by remember { mutableStateOf(null) }
    var saveKey by remember { mutableStateOf(0) }

    LaunchedEffect(saveKey) {
        if (networkId != -1L && placeId != -1L) {
            place = admin?.getPlace(networkId.toULong(), placeId.toULong())
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Информация о ресторане") },
                navigationIcon = { Defaults.BackButton(controller::popBackStack) },
            )
        },
        floatingActionButton = {
            if (name != null || description != null || imageUrl != null) {
                FloatingActionButton(
                    onClick = {
                        save(place, networkId.toULong(), name, description, imageUrl, admin!!, scope, controller)
                        if (place == null) controller.popBackStack()
                        saveKey++
                    },
                ) {
                    Icon(Icons.Outlined.Save, "Сохранить")
                }
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                label = { Text(text = "Название") },
                value = name ?: place?.name.orEmpty(),
                onValueChange = { name = it },
                isError = (name ?: place?.name.orEmpty()).isEmpty(),
            )
            OutlinedTextField(
                label = { Text(text = "Описание") },
                value = description ?: place?.description.orEmpty(),
                onValueChange = { description = it },
                isError = (description ?: place?.description.orEmpty()).isEmpty(),
            )
            OutlinedTextField(
                label = { Text(text = "Ссылка на изображение") },
                value = imageUrl ?: place?.imageUrl.orEmpty(),
                onValueChange = { imageUrl = it },
                isError = (imageUrl ?: place?.imageUrl.orEmpty()).isEmpty(),
            )
            Spacer(Modifier.height(32.dp))
            AsyncImage(
                model = imageUrl ?: place?.imageUrl,
                contentDescription = "Товар",
                modifier = Modifier
                    .aspectRatio(1.78f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                placeholder = ColorPainter(Color(0, 0, 0, 20)),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

private fun save(
    place: Place?,
    networkId: ULong?,
    name: String?,
    description: String?,
    imageUrl: String?,
    admin: AdminClient,
    scope: CoroutineScope,
    controller: NavController?,
) {
    scope.launch {
        val newPlace = if (place == null) admin.createPlace(
            networkId ?: return@launch,
            name?.takeIf { it.isNotEmpty() } ?: return@launch,
            description?.takeIf { it.isNotEmpty() } ?: return@launch,
            imageUrl?.takeIf { it.isNotEmpty() } ?: return@launch,
        ) else admin.editPlace(place.id, place.networkId, name, description, imageUrl)
        if (place == null && newPlace != null) {
            controller?.popBackStack()
            controller?.navigate(AdminRoutes.network.withArgs("id" to newPlace.networkId.toLong()))
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun PlaceEditPagePreview() = OrdersSpaceTheme { PlaceEditPage(rememberNavController()) }