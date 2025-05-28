package com.example.atividadeavaliativa1.repository

import android.content.Context
import com.example.atividadeavaliativa1.room.AppDatabase
import com.example.atividadeavaliativa1.room.CartItem
import com.example.atividadeavaliativa1.repository.retrofit.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class CartRepository(context: Context) {
    private val cartItemDao = AppDatabase.getDatabase(context).cartItemDao()

    fun getAllCartItems(): Flow<List<CartItem>> {
        return cartItemDao.getAllCartItems()
    }

    suspend fun addToCart(product: Product, size: String) {
        val newCartItem = CartItem.fromProduct(product, size)
        
        val currentItems = getAllCartItems().first()
        
        val existingItem = currentItems.find { 
            it.productId == newCartItem.productId && it.size == newCartItem.size 
        }

        if (existingItem != null) {
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
            cartItemDao.insertCartItem(updatedItem)
        } else {
            cartItemDao.insertCartItem(newCartItem)
        }
    }

    suspend fun updateCartItem(cartItem: CartItem) {
        cartItemDao.insertCartItem(cartItem)
    }

    suspend fun removeFromCart(cartItem: CartItem) {
        cartItemDao.deleteCartItem(cartItem)
    }
} 