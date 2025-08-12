package com.bor96dev.presentation.myfridge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bor96dev.domain.Ingredient
import com.bor96dev.domain.Recipe
import com.bor96dev.domain.usecases.ingredients.AddIngredientUseCase
import com.bor96dev.domain.usecases.ingredients.DeleteIngredientUseCase
import com.bor96dev.domain.usecases.ingredients.GetIngredientsUseCase
import com.bor96dev.domain.usecases.recipes.AddRecipeToFavoritesUseCase
import com.bor96dev.domain.usecases.recipes.FindRecipesByIngredientsUseCase
import com.bor96dev.domain.usecases.recipes.RemoveRecipeFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class MyFridgeViewModel @Inject constructor(
    private val getIngredientsUseCase: GetIngredientsUseCase,
    private val addIngredientUseCase: AddIngredientUseCase,
    private val deleteIngredientUseCase: DeleteIngredientUseCase,
    private val addRecipeToFavoritesUseCase: AddRecipeToFavoritesUseCase,
    private val findRecipesByIngredientsUseCase: FindRecipesByIngredientsUseCase,
    private val removeRecipeFromFavoritesUseCase: RemoveRecipeFromFavoritesUseCase
): ViewModel(){

    data class MyFridgeUiState(
        val currentIngredientInput: String = "",
        val searchResults: List<Recipe> = emptyList(),
        val isLoading: Boolean = false
    )

    data class CombinedState(
        val uiState: MyFridgeUiState,
        val ingredients: List<Ingredient>
    )

    private val _uiState = MutableStateFlow(MyFridgeUiState())
    private val ingredientsFlow = getIngredientsUseCase()
    val combinedState = combine(_uiState, ingredientsFlow){ state, ingredients ->
        CombinedState(state, ingredients)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CombinedState(MyFridgeUiState(), emptyList()))

    fun onIngredientInputChange(text: String) {
        _uiState.update { it.copy(currentIngredientInput = text) }
    }

    fun addIngredient() {
        viewModelScope.launch {
            val ingredientName = _uiState.value.currentIngredientInput.trim()
            if (ingredientName.isNotBlank()){
                val ingredient = Ingredient(
                    id = 0,
                    name = ingredientName,
                    amount = 0.0,
                    unit = "",
                    image = ""
                )
                addIngredientUseCase(ingredient)
                _uiState.update {it.copy(currentIngredientInput = "")}
            }
        }


    }

    fun removeIngredient(ingredient: Ingredient) {
        viewModelScope.launch {
            deleteIngredientUseCase(ingredient)
        }
    }

    fun findRecipes() {
        viewModelScope.launch {
            val currentIngredients = combinedState.value.ingredients
            if (currentIngredients.isEmpty()) return@launch

            _uiState.update { it.copy(isLoading = true) }
            val ingredientsString = currentIngredients.joinToString(","){it.name}

            findRecipesByIngredientsUseCase(ingredientsString)
                .catch{ e ->
                    _uiState.update{it.copy(isLoading = false)}
                }
                .collect {recipes ->
                    _uiState.update {
                        it.copy(
                            searchResults = recipes,
                            isLoading = false
                        )
                    }
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

