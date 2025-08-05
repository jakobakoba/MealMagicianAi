package com.bor96dev.domain.usecases

import com.bor96dev.domain.Recipe
import com.bor96dev.domain.RecipeRepository
import javax.inject.Inject

class AddRecipeToFavoritesUseCase  @Inject constructor(
    private val repository: RecipeRepository
){
    suspend operator fun invoke(recipe: Recipe){
        repository.addRecipeToFavorites(recipe)
    }
}