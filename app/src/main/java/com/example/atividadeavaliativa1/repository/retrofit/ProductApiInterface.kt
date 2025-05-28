package com.example.atividadeavaliativa1.repository.retrofit

import retrofit2.http.GET

interface ProductApiInterface {
    @GET("products")
    suspend fun getProducts(): List<Product>
}

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val shopName: String,
    val shopCategory: String,
    val description: String,
    val rating: Double,
    val avaliableSizes: List<String>,
    val image: List<String>,
    val isFavorite: Boolean?
) 