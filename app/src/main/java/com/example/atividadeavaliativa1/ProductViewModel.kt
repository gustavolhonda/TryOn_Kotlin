package com.example.atividadeavaliativa1

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.atividadeavaliativa1.repository.retrofit.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductViewModel() : ViewModel() {
    private val _selectedImage = mutableStateOf<String?>(null)
    val selectedImage: State<String?> get() = _selectedImage


    private val _selectedSize = mutableStateOf<String?>(null)
    val selectedSize: State<String?> get() = _selectedSize

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?>
        get() = _selectedProduct

    private val _cartItems = mutableStateListOf<Pair<Product, String>>()
    val cartItems: List<Pair<Product, String>> = _cartItems

    // Novo: Mapa para gerenciar os estados de favorito por produto ID
    private val _favorites = mutableStateMapOf<Int, Boolean>()
    val favorites: Map<Int, Boolean> get() = _favorites

    fun selectProduct(product: Product) {
        _selectedProduct.value = product

        // Inicializa o estado favorito no mapa se ainda não existir
        if (!_favorites.containsKey(product.id)) {
            _favorites[product.id] = product.isFavorite ?: false
        }
    }

    fun addToCart(product: Product, size: String) {
        _cartItems.add(product to size)
    }

    fun initFavorite(productId: Int, initialValue: Boolean) {
        if (!_favorites.containsKey(productId)) {
            _favorites[productId] = initialValue
        }
    }

    fun toggleFavorite(productId: Int) {
        val current = _favorites[productId] ?: false
        _favorites[productId] = !current
    }

    fun isFavorite(productId: Int): Boolean {
        return _favorites[productId] ?: false
    }

    fun selectSize(size: String?) {
        _selectedSize.value = size
    }

    fun setSelectedImage(imageUrl: String?) {
        _selectedImage.value = imageUrl
    }

    fun initializeSelectedImage(imageUrl: String?) {
        if (_selectedImage.value == null) {
            _selectedImage.value = imageUrl
        }
    }
}
