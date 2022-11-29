@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)

package com.ordersspace.android.ui.customer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SetMeal
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ordersspace.android.ui.theme.OrdersSpaceTheme
import kotlinx.coroutines.launch

@Composable
fun MainMenu(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text("Меню") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Outlined.Search, "Поиск")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Outlined.ShoppingCart, "Корзина")
                    }
                },
            )
        }
    ) { padding ->
        val pagerState = rememberPagerState(3)
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            CategoryTabs(pagerState)
            CategoryItems(pagerState)
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

@Composable
fun CategoryItems(pagerState: PagerState) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { index ->
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
        ) {
            items(100, key = { it }) {
                Text("LOL")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainMenuPreview() {
    OrdersSpaceTheme {
        MainMenu(rememberNavController())
    }
}