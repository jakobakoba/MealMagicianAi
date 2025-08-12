package com.bor96dev.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bor96dev.domain.Recipe
import com.bor96dev.domain.usecases.recipes.GetFavoriteRecipesUseCase
import com.bor96dev.domain.usecases.recipes.RemoveRecipeFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteRecipesViewModel @Inject constructor(
    private val getFavoriteRecipesUseCase: GetFavoriteRecipesUseCase,
    private val removeRecipeFromFavoritesUseCase: RemoveRecipeFromFavoritesUseCase
): ViewModel() {

    data class FavoriteRecipesUiState(
        val recipes: List<Recipe> = emptyList(),
        val isLoading: Boolean = false
    )

    val uiState: StateFlow<FavoriteRecipesUiState> =
        getFavoriteRecipesUseCase()
            .map{favoriteRecipes ->
                FavoriteRecipesUiState(recipes = favoriteRecipes)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = FavoriteRecipesUiState(isLoading = true)
            )

    fun removeRecipeFromFavorites(recipe: Recipe){
        viewModelScope.launch {
            removeRecipeFromFavoritesUseCase(recipe)
        }
    }
}