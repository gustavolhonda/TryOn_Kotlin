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

@Composable
fun App (
    navController : NavHostController = rememberNavController(),
    startingRoute : String = "login"
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val scope = rememberCoroutineScope()

    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startingRoute
        ) {
            composable("login") {
                LoginScreen(
                    onSuccessfulLogin = {
                        navController.navigate("main") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    },
                    onForgetPasswordButtonClick = {
                        navController.navigate("forgetPassword")
                    },
                    onRegisterButtonClick = {
                        navController.navigate("registerUser")
                    }
                )
            }
//            composable("main") {
//                MainScreen(
//                    navigateUp = {
//                        scope.launch {
//                            activity?.finish()
//                        }
//                    }
//                )
//            }
//            composable("forgetPassword") {
//                ForgetPasswordScreen(
//                    navigateUp = {
//                        navController.popBackStack()
//                    }
//                )
//            }
//            composable("registerUser") {
//                RegisterUserScreen(
//                    navigateUp = {
//                        navController.popBackStack()
//                    }
//                )
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AtividadeAvaliativa1Theme {
        com.example.atividadeavaliativa1.App()
    }
}