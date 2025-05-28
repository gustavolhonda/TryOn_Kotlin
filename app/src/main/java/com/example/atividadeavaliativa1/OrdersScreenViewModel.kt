package com.example.atividadeavaliativa1

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.atividadeavaliativa1.repository.retrofit.Product

val mockOrders = listOf(
    Product(
        id = 1,
        name = "Product 1",
        price = 100.0,
        shopName = "Shop 1",
        shopCategory = "Category 1",
        description = "Description of Product 1",
        rating = 4.5,
        avaliableSizes = listOf("S", "M", "L"),
        image = listOf("https://m.media-amazon.com/images/I/41Tbr4iFggL._AC_.jpg"),
        isFavorite = false,
    ),
    Product(
        id = 2,
        name = "Product 2",
        price = 200.0,
        shopName = "Shop 2",
        shopCategory = "Category 2",
        description = "Description of Product 2",
        rating = 4.0,
        avaliableSizes = listOf("M", "L", "XL"),
        image = listOf("https://m.media-amazon.com/images/I/41Tbr4iFggL._AC_.jpg"),
        isFavorite = false,
    ),
    Product(
        id = 3,
        name = "Product 3",
        price = 300.0,
        shopName = "Shop 3",
        shopCategory = "Category 3",
        description = "Description of Product 3",
        rating = 4.0,
        avaliableSizes = listOf("M", "L", "XL"),
        image = listOf("https://m.media-amazon.com/images/I/41Tbr4iFggL._AC_.jpg"),
        isFavorite = false,
    ),
    Product(
        id = 4,
        name = "Product 4",
        price = 400.0,
        shopName = "Shop 4",
        shopCategory = "Category 4",
        description = "Description of Product 4",
        rating = 4.0,
        avaliableSizes = listOf("M", "L", "XL"),
        image = listOf("https://m.media-amazon.com/images/I/41Tbr4iFggL._AC_.jpg"),
        isFavorite = false,
    )
)

class OrdersScreenViewModel : ViewModel() {
    val quantities = mutableStateMapOf<Int, Int>().apply {
        putAll(mockOrders.associate { it.id to 1 })
    }

    val orders = mockOrders

    val totalPrice: Double
        get() = orders.sumOf { product ->
            (quantities[product.id]?.toDouble() ?: 0.0) * product.price
        }

    fun increaseQuantity(productId: Int) {
        quantities[productId] = (quantities[productId] ?: 0) + 1
    }

    fun decreaseQuantity(productId: Int) {
        val currentQuantity = quantities[productId] ?: 0
        if (currentQuantity > 1) {
            quantities[productId] = currentQuantity - 1
        }
    }
}