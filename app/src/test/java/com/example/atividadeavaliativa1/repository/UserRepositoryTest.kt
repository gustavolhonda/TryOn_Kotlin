package com.example.atividadeavaliativa1.repository

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

class UserRepositoryTest {

    private val userRepository = UserRepository(true)

    @Test
    fun loginSuccessful() = runBlocking {
        val result = userRepository.login("user1@gmail.com", "123")
        assertEquals("success", result)
    }

    @Test
    fun loginWrongUsername() = runBlocking {
        val result = userRepository.login("gustavo", "123")
        assertEquals("wrong_username", result)
    }

    @Test
    fun loginWrongPassword() = runBlocking {
        val result = userRepository.login("user1@gmail.com", "123wrong")
        assertEquals("wrong_password", result)
    }
}