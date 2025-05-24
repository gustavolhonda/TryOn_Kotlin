package com.example.atividadeavaliativa1

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atividadeavaliativa1.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel() {
    var username by mutableStateOf("")
    var usernameError by mutableStateOf(false)
    var usernameErrorMessage by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordError by mutableStateOf(false)
    var passwordErrorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var isLoginSuccessful by mutableStateOf(false)

    val userRepository = UserRepository()

    fun performLogin() {
        usernameError = false
        passwordError = false

        username = username.trim()

        if (username == "") {
            usernameError = true
            usernameErrorMessage = "Este campo é obrigatório"
            return
        }

        if (password == "") {
            passwordError = true
            passwordErrorMessage = "Este campo é obrigatório"
            return
        }

        viewModelScope.launch {
            isLoading = true
            val status = userRepository.login(username, password)
            isLoading = false
            Log.i("MainViewModel", "name: $username, password: $password, status: $status")
            when(status) {
                "success" -> {
                    Log.i("MainViewModel", "moving to main screen")
                    isLoginSuccessful = true
                }
                "wrong_username" -> {
                    usernameError = true
                    usernameErrorMessage = "wrong user name"
                }
                "wrong_password" -> {
                    passwordError = true
                    passwordErrorMessage = "wrong password"
                }
                else -> {
                    usernameError = true
                    usernameErrorMessage = "Error: $status"
                }
            }
        }
    }
}