package com.bor96dev.presentation.myfridge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bor96dev.domain.Recipe
import com.bor96dev.domain.usecases.AddRecipeToFavoritesUseCase
import com.bor96dev.domain.usecases.FindRecipesByIngredientsUseCase
import com.bor96dev.domain.usecases.RemoveRecipeFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyFridgeViewModel @Inject constructor(
    private val addRecipeToFavoritesUseCase: AddRecipeToFavoritesUseCase,
    private val findRecipesByIngredientsUseCase: FindRecipesByIngredientsUseCase,
    private val removeRecipeFromFavoritesUseCase: RemoveRecipeFromFavoritesUseCase
): ViewModel(){

    data class MyFridgeUiState(
        val ingredients: List<String> = emptyList(),
        val currentIngredientInput: String = "",
        val searchResults: List<Recipe> = emptyList(),
        val isLoading: Boolean = false
    )

    private val _uiState = MutableStateFlow(MyFridgeUiState())
    val uiState = _uiState.asStateFlow()

    fun onIngredientInputChange(text: String) {
        _uiState.update { it.copy(currentIngredientInput = text) }
    }

    fun addIngredient() {
        val ingredient = _uiState.value.currentIngredientInput.trim()
        if (ingredient.isNotBlank() && ingredient !in _uiState.value.ingredients) {
            _uiState.update {
                it.copy(
                    ingredients = it.ingredients + ingredient,
                    currentIngredientInput = ""
                )
            }
        }
    }

    fun removeIngredient(ingredient: String) {
        _uiState.update {
            it.copy(ingredients = it.ingredients - ingredient)
        }
    }

    fun findRecipes() {
        if (_uiState.value.ingredients.isEmpty()) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val ingredientsString = _uiState.value.ingredients.joinToString(",")
                val recipes = findRecipesByIngredientsUseCase(ingredientsString)
                _uiState.update { it.copy(searchResults = recipes, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun addRecipeToFavorites(recipe: Recipe){
        viewModelScope.launch {
            addRecipeToFavoritesUseCase(recipe)
        }
    }

    fun removeRecipeFromFavorites(recipe: Recipe){
        viewModelScope.launch {
            removeRecipeFromFavoritesUseCase(recipe)
        }
    }
}