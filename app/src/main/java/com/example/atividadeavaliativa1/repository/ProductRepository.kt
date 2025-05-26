package com.example.atividadeavaliativa1.repository

import com.example.atividadeavaliativa1.repository.retrofit.Product
import com.example.atividadeavaliativa1.repository.retrofit.ProductApiInterface
import com.example.atividadeavaliativa1.repository.retrofit.RetrofitInstance

class ProductRepository(val useTestUrl: Boolean = false) {
    private var client: ProductApiInterface

    init {
        // client = if (useTestUrl) RetrofitInstance.testProductApi else RetrofitInstance.productApi
        client = RetrofitInstance.productApi
    }

    suspend fun getProducts(): List<Product> {
        try {
            return client.getProducts()
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }
} 