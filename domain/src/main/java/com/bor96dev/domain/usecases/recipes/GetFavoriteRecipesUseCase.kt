package com.bor96dev.domain.usecases.recipes

import com.bor96dev.domain.Recipe
import com.bor96dev.domain.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(): Flow<List<Recipe>> {
        return repository.getFavoriteRecipes()
    }
}