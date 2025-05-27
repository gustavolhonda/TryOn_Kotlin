package com.example.atividadeavaliativa1

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.atividadeavaliativa1.repository.retrofit.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductViewModel(): ViewModel() {
    // MutableStateFlow ->  fluxo (stream) de dados que mantém o estado atual e notifica os observadores toda vez que esse estado muda.
    private val _selectedProduct = MutableStateFlow<Product?>(null)
    //  interface somente leitura exposta para outras partes do código
    val selectedProduct: StateFlow<Product?>
        get() = _selectedProduct


    //  única forma segura de modificar o valor de _selectedProduct.
    fun selectProduct(product: Product) {
        _selectedProduct.value = product
    }
}