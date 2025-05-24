package com.example.atividadeavaliativa1.repository

import com.example.atividadeavaliativa1.repository.retrofit.LoginApiInterface
import com.example.atividadeavaliativa1.repository.retrofit.RetrofitInstance
import com.example.atividadeavaliativa1.repository.retrofit.UserData

class UserRepository (val useTestUrl: Boolean = false) {
    private var client: LoginApiInterface

    init {
        client = if (useTestUrl) RetrofitInstance.testapi else RetrofitInstance.api
    }

    // TODO: USE RESPONSE STATUS TOO
    suspend fun login(username : String, password : String) : String {
        try {
            val res = client.login(UserData(username, password))
            return when(res.message) {
                "success" -> "success"
                "unexisting username" -> "wrong_username"
                "wrong password" -> "wrong_password"
                else -> res.message
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "failed_connection"
        }
    }
}