package com.bor96dev.presentation.myfridge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bor96dev.domain.Recipe
import com.bor96dev.domain.usecases.FindRecipesByIngredientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyFridgeViewModel @Inject constructor(
    private val findRecipesByIngredientsUseCase: FindRecipesByIngredientsUseCase
): ViewModel(){

    private val _products = MutableStateFlow<List<String>>(emptyList())
    val products = _products.asStateFlow()

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes = _recipes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun addProduct(product: String) {
        if (product.isNotBlank() && !_products.value.contains(product)){
            _products.value = _products.value + product
        }
    }

    fun removeProduct(product: String){
        _products.value = _products.value - product
    }

    fun findRecipes(){
        viewModelScope.launch {
            _isLoading.value = true
            val ingredients = products.value.joinToString(",")
            try {
                val result = findRecipesByIngredientsUseCase(ingredients)
                _recipes.value = result
            } catch (e: Exception){
                _recipes.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}