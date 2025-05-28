package com.example.atividadeavaliativa1.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atividadeavaliativa1.repository.ProductRepository
import com.example.atividadeavaliativa1.repository.retrofit.Product
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    var products by mutableStateOf<List<Product>>(emptyList())
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    var selectedTabIndex by mutableStateOf(0)
    val tabTitles = listOf(
        "Todos",
        "Promoções",
        "Lojas"
    )

    private val productRepository = ProductRepository()

    fun loadProducts() {
        viewModelScope.launch {
            isLoading = true
            try {
                products = productRepository.getProducts()
                error = null
            } catch (e: Exception) {
                Log.e("HomeScreenViewModel", "Erro ao carregar produtos", e)
                error = "Erro ao carregar produtos"
            } finally {
                isLoading = false
            }
        }
    }

    fun updateSelectedTab(index: Int) {
        selectedTabIndex = index
    }
} 