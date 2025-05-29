package com.example.atividadeavaliativa1.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.atividadeavaliativa1.room.CartItem
import com.example.atividadeavaliativa1.repository.CartRepository
import com.example.atividadeavaliativa1.repository.retrofit.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    // mantém a URL da imagem selecionada para mostrar na tela.
    private val _selectedImage = mutableStateOf<String?>(null)
    val selectedImage: State<String?> get() = _selectedImage

    // mapa que armazena o tamanho selecionado para cada produto (chave = id do produto).
    private val _selectedSizes = mutableStateMapOf<Int, String?>()
    val selectedSizes: Map<Int, String?> get() = _selectedSizes

    // StateFlow que guarda o produto atualmente selecionado (fluxo reativo).
    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?>
        get() = _selectedProduct

    // lista observável dos itens no carrinho.
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> = _cartItems

    // Novo: mapa que guarda o estado "favorito" de cada produto pelo seu id.
    private val _favorites = mutableStateMapOf<Int, Boolean>()
    val favorites: Map<Int, Boolean> get() = _favorites

    private val _showToast = mutableStateOf(false)
    val showToast: State<Boolean> = _showToast

    private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String> = _toastMessage

    private val cartRepository = CartRepository(application)

    init {
        viewModelScope.launch {
            cartRepository.getAllCartItems().collectLatest { items ->
                _cartItems.clear()
                _cartItems.addAll(items)
            }
        }
    }

    fun selectProduct(product: Product) {
        _selectedProduct.value = product

        // Inicializa o estado favorito no mapa se ainda não existir
        if (!_favorites.containsKey(product.id)) {
            _favorites[product.id] = product.isFavorite ?: false
        }
    }

    fun addToCart(product: Product, size: String) {
        viewModelScope.launch {
            cartRepository.addToCart(product, size)
            // Busca e mostra todos os itens do carrinho
            cartRepository.getAllCartItems().collectLatest { items ->
                Log.d("CARRINHO", """
                    ============================================
                    ITENS NO CARRINHO:
                    ${items.joinToString("\n") { item ->
                        """
                        ID: ${item.id}
                        Nome: ${item.name}
                        Preço: R$ ${item.price}
                        Tamanho: ${item.size}
                        Loja: ${item.shopName}
                        --------------------------
                        """.trimIndent()
                    }}
                    ============================================
                """.trimIndent())

                _toastMessage.value = "Produto adicionado ao carrinho!"
                _showToast.value = true
            }
        }
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

    fun selectSize(productId: Int, size: String?) {
        if (size == null) {
            _selectedSizes.remove(productId)
        } else {
            _selectedSizes[productId] = size
        }
    }

    fun getSelectedSize(productId: Int): String? {
        return _selectedSizes[productId]
    }


    fun setSelectedImage(imageUrl: String?) {
        _selectedImage.value = imageUrl
    }

    fun initializeSelectedImage(imageUrl: String?) {
        _selectedImage.value = imageUrl
    }

    fun resetToast() {
        _showToast.value = false
    }
}
