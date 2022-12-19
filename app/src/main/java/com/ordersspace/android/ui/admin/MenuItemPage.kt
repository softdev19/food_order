@file:OptIn(ExperimentalMaterial3Api::class)

package com.ordersspace.android.ui.admin

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ordersspace.android.client.AdminClient
import com.ordersspace.android.model.MenuItem
import com.ordersspace.android.ui.Defaults
import com.ordersspace.android.ui.navigation.AdminRoutes
import com.ordersspace.android.ui.rememberAdmin
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import com.ordersspace.android.ui.withArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuItemPage(controller: NavController, networkId: Long = -1L, itemId: Long = -1L) {
    val admin = rememberAdmin()
    val scope = rememberCoroutineScope()
    var item: MenuItem? by remember { mutableStateOf(null) }
    var menuExpanded by remember { mutableStateOf(false) }

    val (name, setName) = remember { mutableStateOf<String?>(null) }
    var type by remember { mutableStateOf<MenuItem.ItemType?>(null) }
    val (cost, setCost) = remember { mutableStateOf<String?>(null) }
    val (weight, setWeight) = remember { mutableStateOf<String?>(null) }
    val (volume, setVolume) = remember { mutableStateOf<String?>(null) }
    val (description, setDescription) = remember { mutableStateOf<String?>(null) }
    val (isAgeRestricted, setIsAgeRestricted) = remember { mutableStateOf(item?.isAgeRestricted ?: false) }
    val (imageUrl, setImageUrl) = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(0) {
        if (itemId != -1L) item = admin?.getMenuItem(networkId.toULong(), itemId.toULong())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Информация о товаре") },
                navigationIcon = { Defaults.BackButton(controller::popBackStack) },
            )
        },
        floatingActionButton = {
            if (listOfNotNull(name, type, cost, weight, volume, description, imageUrl).any()
                || isAgeRestricted != item?.isAgeRestricted) {
                FloatingActionButton(
                    onClick = {
                        save(
                            item,
                            networkId.toULong(),
                            name, type,
                            cost?.toDoubleOrNull(),
                            weight?.toDoubleOrNull(),
                            volume?.toDoubleOrNull(),
                            description, isAgeRestricted,
                            imageUrl, admin!!,
                            scope, controller
                        )
                    },
                ) {
                    Icon(Icons.Outlined.Save, "Сохранить")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(rememberScrollState(), Orientation.Vertical)
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                label = { Text(text = "Название") },
                value = name ?: item?.name.orEmpty(),
                onValueChange = setName,
                singleLine = true,
            )
            ExposedDropdownMenuBox(
                expanded = menuExpanded,
                onExpandedChange = { menuExpanded = it },
            ) {
                OutlinedTextField(
                    value = type?.displayName ?: item?.type?.displayName.orEmpty(),
                    onValueChange = {},
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    label = { Text("Тип") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(menuExpanded) },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    singleLine = true,
                )
                ExposedDropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false },
                ) {
                    MenuItem.ItemType.values().forEach {
                        DropdownMenuItem(
                            text = { Text(it.displayName) },
                            onClick = { type = it; menuExpanded = false },
                        )
                    }
                }
            }
            OutlinedTextField(
                label = { Text(text = "Стоимость") },
                value = cost ?: item?.cost?.toString().orEmpty(),
                onValueChange = setCost,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true,
            )
            OutlinedTextField(
                label = { Text(text = "Масса") },
                value = weight ?: item?.weight?.toString().orEmpty(),
                onValueChange = setWeight,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true,
            )
            OutlinedTextField(
                label = { Text(text = "Объем") },
                value = volume ?: item?.volume?.toString().orEmpty(),
                onValueChange = setVolume,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true,
            )
            OutlinedTextField(
                label = { Text(text = "Описание") },
                value = description ?: item?.description.orEmpty(),
                onValueChange = setDescription,
            )
            OutlinedTextField(
                label = { Text(text = "Ссылка на картинку") },
                value = imageUrl ?: item?.imageUrl.orEmpty(),
                onValueChange = setImageUrl,
                singleLine = true,
            )
            Row(
                Modifier
                    .fillMaxWidth(0.75f)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Товар для взрослых", Modifier.weight(1f))
                Checkbox(
                    checked = isAgeRestricted,
                    onCheckedChange = setIsAgeRestricted,
                )
            }
            AsyncImage(
                model = imageUrl ?: item?.imageUrl,
                contentDescription = "Товар",
                modifier = Modifier
                    .aspectRatio(1.0f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(Color(0, 0, 0, 20)),
            )
        }
    }
}

private fun save(
    item: MenuItem?,
    networkId: ULong?,
    name: String?,
    type: MenuItem.ItemType?,
    cost: Double?,
    weight: Double?,
    volume: Double?,
    description: String?,
    isAgeRestricted: Boolean?,
    imageUrl: String?,
    admin: AdminClient,
    scope: CoroutineScope,
    controller: NavController?,
) {
    scope.launch {
        val newItem = if (item == null) admin.createMenuItem(
            networkId ?: return@launch,
            name?.takeIf { it.isNotEmpty() } ?: return@launch,
            type ?: return@launch,
            cost ?: return@launch,
            weight ?: return@launch,
            volume ?: return@launch,
            description?.takeIf { it.isNotEmpty() } ?: return@launch,
            isAgeRestricted ?: return@launch,
            imageUrl?.takeIf { it.isNotEmpty() } ?: return@launch,
        ) else return@launch // TODO: edit menu items
        if (item == null && newItem != null) {
            controller?.popBackStack()
            controller?.navigate(AdminRoutes.network.withArgs("id" to newItem.networkId))
        }
    }
}

@Preview(showSystemUi = true, name = "SettingsItemPage")
@Composable
fun MenuItemPagePreview() = OrdersSpaceTheme { MenuItemPage(rememberNavController()) }