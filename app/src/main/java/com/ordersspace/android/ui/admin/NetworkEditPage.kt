@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.ordersspace.android.model.Network
import com.ordersspace.android.ui.Defaults
import com.ordersspace.android.ui.navigation.AdminRoutes
import com.ordersspace.android.ui.rememberAdmin
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import com.ordersspace.android.ui.withArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview(showSystemUi = true, name = "Admin page")
@Composable
fun NetworkEditPagePreview() = OrdersSpaceTheme { NetworkEditPage(rememberNavController()) }

@Composable
fun NetworkEditPage(
    controller: NavController,
    networkId: Long = -1,
) {
    val scope = rememberCoroutineScope()
    val admin = rememberAdmin()
    var network: Network? by remember { mutableStateOf(null) }
    var saveKey by remember { mutableStateOf(0) }

    val (name, setName) = remember { mutableStateOf<String?>(null) }
    val (description, setDescription) = remember { mutableStateOf<String?>(null) }
    val (imageUrl, setImageUrl) = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(saveKey) {
        if (networkId != -1L) network = admin?.getNetwork(networkId.toULong())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Информация о сети") },
                navigationIcon = { Defaults.BackButton(controller::popBackStack) }
            )
        },
        floatingActionButton = {
            if (name != null || description != null || imageUrl != null) {
                FloatingActionButton(
                    onClick = {
                        save(network, name, description, imageUrl, admin!!, scope, controller)
                        if (network == null) controller.popBackStack()
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
                value = name ?: network?.name.orEmpty(),
                onValueChange = setName,
                isError = (name ?: network?.name.orEmpty()).isEmpty(),
            )
            OutlinedTextField(
                label = { Text(text = "Описание") },
                value = description ?: network?.description.orEmpty(),
                onValueChange = setDescription,
                isError = (description ?: network?.description.orEmpty()).isEmpty(),
            )
            OutlinedTextField(
                label = { Text(text = "Ссылка на изображение") },
                value = imageUrl ?: network?.imageUrl.orEmpty(),
                onValueChange = setImageUrl,
                isError = (imageUrl ?: network?.imageUrl.orEmpty()).isEmpty(),
            )
            Spacer(Modifier.height(32.dp))
            AsyncImage(
                model = imageUrl ?: network?.imageUrl,
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
    network: Network?,
    name: String?,
    description: String?,
    imageUrl: String?,
    admin: AdminClient,
    scope: CoroutineScope,
    controller: NavController?,
) {
    scope.launch {
        val newNetwork = if (network == null) admin.createNetwork(
            name?.takeIf { it.isNotEmpty() } ?: return@launch,
            description?.takeIf { it.isNotEmpty() } ?: return@launch,
            imageUrl?.takeIf { it.isNotEmpty() } ?: return@launch,
        ) else admin.editNetwork(network.id, name, description, imageUrl)
        if (network == null && newNetwork != null) {
            controller?.popBackStack()
            controller?.navigate(AdminRoutes.network.withArgs("id" to newNetwork.id))
        }
    }
}
