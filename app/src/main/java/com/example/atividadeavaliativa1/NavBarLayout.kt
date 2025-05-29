package com.example.atividadeavaliativa1

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.atividadeavaliativa1.screens.HomeScreen
import com.example.atividadeavaliativa1.screens.NotificationsScreen
import com.example.atividadeavaliativa1.screens.OrdersScreen
import com.example.atividadeavaliativa1.viewmodels.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBarLayout(navController: NavController, productViewModel: ProductViewModel) {
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFFCEAE5)
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = stringResource(R.string.navbar_home_description)) },
                    label = { Text(stringResource(R.string.navbar_home_description)) },
                    selected = selectedItem == 0,
                    onClick = {
                        selectedItem = 0
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                        indicatorColor = Color(0xFFFFDBD1)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = stringResource(R.string.navbar_orders_description)) },
                    label = { Text(stringResource(R.string.navbar_orders_description)) },
                    selected = selectedItem == 1,
                    onClick = {
                        selectedItem = 1
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                        indicatorColor = Color(0xFFFFDBD1)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Notifications, contentDescription = stringResource(R.string.navbar_notifications_description)) },
                    label = { Text(stringResource(R.string.navbar_notifications_description)) },
                    selected = selectedItem == 2,
                    onClick = {
                        selectedItem = 2
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                        indicatorColor = Color(0xFFFFDBD1)
                    )
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedItem) {
                0 -> HomeScreen(navController, productViewModel)
                1 -> OrdersScreen()
                2 -> NotificationsScreen()
            }
        }
    }
} 