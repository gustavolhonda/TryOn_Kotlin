package com.example.atividadeavaliativa1

import android.app.Activity
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.atividadeavaliativa1.ui.theme.AtividadeAvaliativa1Theme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Orders : Screen("orders")
    object Notifications : Screen("notifications")
}

@Composable
fun App(
    navController: NavHostController = rememberNavController(),
    startingRoute: String = "login"
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val scope = rememberCoroutineScope()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startingRoute
        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    onSuccessfulLogin = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Home.route) {
                NavBarLayout(navController)
            }
            composable(Screen.Orders.route) {
                NavBarLayout(navController)
            }
            composable(Screen.Notifications.route) {
                NavBarLayout(navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AtividadeAvaliativa1Theme {
        App()
    }
}