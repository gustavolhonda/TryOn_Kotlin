package com.example.atividadeavaliativa1.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.atividadeavaliativa1.repository.retrofit.Product

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Int,
    val name: String,
    val price: Double,
    val shopName: String,
    val shopCategory: String,
    val description: String,
    val rating: Double,
    val size: String,
    val imageUrl: String
) {
    companion object {
        fun fromProduct(product: Product, size: String): CartItem {
            return CartItem(
                productId = product.id,
                name = product.name,
                price = product.price,
                shopName = product.shopName,
                shopCategory = product.shopCategory,
                description = product.description,
                rating = product.rating,
                size = size,
                imageUrl = product.image.firstOrNull() ?: ""
            )
        }
    }
} 