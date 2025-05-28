package com.example.atividadeavaliativa1

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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
    object RegisterUser : Screen("register_user")
    object ForgetPassword : Screen("forget_password")
    object Product : Screen("product")
}

@Composable
fun App(
    navController: NavHostController = rememberNavController(),
    startingRoute: String = "login"
) {

    val productViewModel: ProductViewModel = viewModel()

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
                NavBarLayout(navController, productViewModel)
            }
            composable(Screen.Orders.route) {
                NavBarLayout(navController, productViewModel)
            }
            composable(Screen.Notifications.route) {
                NavBarLayout(navController, productViewModel)
            }
            composable(Screen.ForgetPassword.route) {
                ForgetPasswordScreen()
            }
            composable(Screen.RegisterUser.route) {
                RegisterScreen()
            }
            composable(Screen.Product.route) {
                val product = productViewModel.selectedProduct.collectAsState().value

                if (product != null) {
                    ProductScreen(product, productViewModel, onBackClick = { navController.popBackStack() })
                } else {
                    LaunchedEffect(Unit) {
                        if (!navController.popBackStack()) {
                            navController.navigate(Screen.Home.route)
                        }
                    }
                }
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