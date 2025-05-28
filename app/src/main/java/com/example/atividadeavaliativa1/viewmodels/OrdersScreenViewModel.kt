package com.example.atividadeavaliativa1.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.atividadeavaliativa1.room.CartItem
import com.example.atividadeavaliativa1.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class OrdersScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val cartRepository = CartRepository(application)
    
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    init {
        viewModelScope.launch {
            cartRepository.getAllCartItems().collectLatest { items ->
                _cartItems.value = items
            }
        }
    }

    val totalPrice: Double
        get() = _cartItems.value.sumOf { item ->
            item.price * item.quantity
        }

    fun increaseQuantity(itemId: Long) {
        viewModelScope.launch {
            _cartItems.value.find { it.id == itemId }?.let { item ->
                val updatedItem = item.copy(quantity = item.quantity + 1)
                cartRepository.updateCartItem(updatedItem)
            }
        }
    }

    fun decreaseQuantity(itemId: Long) {
        viewModelScope.launch {
            _cartItems.value.find { it.id == itemId }?.let { item ->
                if (item.quantity > 1) {
                    val updatedItem = item.copy(quantity = item.quantity - 1)
                    cartRepository.updateCartItem(updatedItem)
                } else {
                    cartRepository.removeFromCart(item)
                }
            }
        }
    }
}