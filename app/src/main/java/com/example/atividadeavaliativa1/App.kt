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
    object RegisterUser: Screen("register_user")
    object ForgetPassword: Screen("forget_password")
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
                    },
                    onForgetPasswordButtonClick = {
                        navController.navigate(Screen.ForgetPassword.route)
                    },
                    onRegisterButtonClick = {
                        navController.navigate(Screen.RegisterUser.route)
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
            composable(Screen.ForgetPassword.route) {
                ForgetPasswordScreen()
            }
            composable(Screen.RegisterUser.route) {
                RegisterScreen()
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