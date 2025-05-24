package com.example.atividadeavaliativa1

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atividadeavaliativa1.repository.UserRepository
import kotlinx.coroutines.launch

enum class LoginError {
    NONE,
    REQUIRED_FIELD,
    WRONG_EMAIL,
    WRONG_PASSWORD,
    UNKNOWN
}


class MainViewModel(): ViewModel() {
    var username by mutableStateOf("")
    var usernameError by mutableStateOf(LoginError.NONE)
    var password by mutableStateOf("")
    var passwordError by mutableStateOf(LoginError.NONE)
    var isLoading by mutableStateOf(false)
    var isLoginSuccessful by mutableStateOf(false)

    val userRepository = UserRepository()

    fun performLogin() {
        usernameError = LoginError.NONE
        passwordError = LoginError.NONE

        username = username.trim()

        if (username == "") {
            usernameError = LoginError.REQUIRED_FIELD
            return
        }

        if (password == "") {
            passwordError = LoginError.REQUIRED_FIELD
            return
        }

        viewModelScope.launch {
            isLoading = true
            val status = userRepository.login(username, password)
            isLoading = false
            Log.i("MainViewModel", "name: $username, password: $password, status: $status")
            when(status) {
                "success" -> isLoginSuccessful = true
                "wrong_username" -> usernameError = LoginError.WRONG_EMAIL
                "wrong_password" -> passwordError = LoginError.WRONG_PASSWORD
                else -> usernameError = LoginError.UNKNOWN
            }
        }
    }
}