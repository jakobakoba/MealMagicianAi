package com.bor96dev.presentation.myfridge

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyFridgeViewModel @Inject constructor(): ViewModel(){

    private val _products = MutableStateFlow<List<String>>(emptyList())
    val products = _products.asStateFlow()

    fun addProduct(product: String) {
        if (product.isNotBlank() && !_products.value.contains(product)){
            _products.value = _products.value + product
        }
    }

    fun removeProduct(product: String){
        _products.value = _products.value - product
    }
}