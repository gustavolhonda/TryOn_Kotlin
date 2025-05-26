package com.example.atividadeavaliativa1.repository

import com.example.atividadeavaliativa1.repository.retrofit.LoginApiInterface
import com.example.atividadeavaliativa1.repository.retrofit.RetrofitInstance
import com.example.atividadeavaliativa1.repository.retrofit.UserData

class UserRepository (val useTestUrl: Boolean = false) {
    private var client: LoginApiInterface

    init {
        client = if (useTestUrl) RetrofitInstance.testapi else RetrofitInstance.api
    }

    suspend fun login(username: String, password: String): String {
        return try {
            val response = client.login(UserData(username, password))
            if (response.isSuccessful) {
                val res = response.body()
                when (res?.message) {
                    "success" -> "success"
                    else -> "unknown_success"
                }
            } else {
                when (response.code()) {
                    400 -> "missing_fields"
                    404 -> "wrong_username"
                    401 -> "wrong_password"
                    else -> "error_${response.code()}"
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "failed_connection"
        }
    }
}