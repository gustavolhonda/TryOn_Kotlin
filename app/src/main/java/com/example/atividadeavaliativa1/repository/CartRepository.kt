package com.example.atividadeavaliativa1.repository

import android.content.Context
import com.example.atividadeavaliativa1.room.AppDatabase
import com.example.atividadeavaliativa1.room.CartItem
import com.example.atividadeavaliativa1.repository.retrofit.Product
import kotlinx.coroutines.flow.Flow

class CartRepository(context: Context) {
    private val cartItemDao = AppDatabase.getDatabase(context).cartItemDao()

    fun getAllCartItems(): Flow<List<CartItem>> {
        return cartItemDao.getAllCartItems()
    }

    suspend fun addToCart(product: Product, size: String) {
        val cartItem = CartItem.fromProduct(product, size)
        cartItemDao.insertCartItem(cartItem)
    }
} 