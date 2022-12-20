@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)

package com.ordersspace.android.ui.customer

import android.content.ClipData
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ordersspace.android.model.Cart
import com.ordersspace.android.model.MenuItem
import com.ordersspace.android.ui.navigation.CustomerRoutes
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import kotlinx.coroutines.launch

@Composable
fun MenuPage(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text("Меню") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Outlined.Search, "Поиск")
                    }
                    IconButton(onClick = { navController.navigate(CustomerRoutes.cart) }) {
                        BadgedBox(badge = { Badge { Text("0") } }) {
                            Icon(Icons.Outlined.ShoppingCart, "Корзина")
                        }
                    }
                },
            )
        }
    ) { padding ->
        val pagerState = rememberPagerState(3)

        val item = MenuItem(
            id = 0UL,
            name = "Бургургер",
            type = MenuItem.ItemType.DISH,
            cost = 69.99,
            weight = 69.99,
            volume = 69.99,
            description = "best food ever",
            isAgeRestricted = false,
            imageUrl = "https://i.imgur.com/L5IhOun.png",
            networkId = 0UL,
        )
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            CategoryTabs(pagerState)
            CategoryItems(
                pagerState, item
            )
        }
    }
}

@Composable
fun CategoryTabs(pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier.fillMaxWidth(),
    ) {
        (0 until pagerState.pageCount).forEach {
            Tab(
                selected = pagerState.currentPage == it,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                },
                text = { Text("Tab") },
                icon = { Icon(Icons.Outlined.SetMeal, "tab icon") },
            )
        }
    }
}
object counter{
    val cart = mapOf<ULong,Int>()
}

@Composable
fun CategoryItems(pagerState: PagerState, item: MenuItem) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { index ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(5, key = { it }) {
                ItemCard(item, onClick = { /* TODO: navigate to item page */ })
            }
        }
    }
}

@Composable
fun ItemCard(item: MenuItem, onClick: () -> Unit,) {
    Card(
        onClick,
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = "Товар",
                modifier = Modifier
                    .aspectRatio(1.78f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                placeholder = ColorPainter(Color(0, 0, 0, 20)),
            )
            Box(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = item.name,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.headlineMedium,
                )
                IconButton(onClick = { Cart += item.id }, Modifier.background(
                    Color.Transparent),) {
                    Icon(Icons.Outlined.Add, "Увеличить на 1",Modifier.size(12.dp))
                }
                Text(Cart[item.id].toString())
                IconButton(onClick = { Cart -= item.id }, Modifier.background(
                    Color.Transparent),) {
                   Icon (Icons.Outlined.Remove, "Уменьшить на 1",Modifier.size(12.dp))
                }
                TextButton(onClick = {Cart += item.id }) {
                    Icon(Icons.Outlined.AddShoppingCart, "Добавить в корзину")
                    Box(modifier = Modifier.width(8.dp))
                    Text(item.cost.toString())
                }
                if (item.id in Cart) {
                    Cart[item.id]
                } else {
                    Cart += item.id
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun MenuPagePreview() = OrdersSpaceTheme { MenuPage(rememberNavController()) }