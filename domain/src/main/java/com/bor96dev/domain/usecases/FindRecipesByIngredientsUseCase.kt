package com.bor96dev.domain.usecases

import com.bor96dev.domain.Recipe
import com.bor96dev.domain.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindRecipesByIngredientsUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(ingredients: String): Flow<List<Recipe>> {
        return repository.findRecipesByIngredients(ingredients)
    }
}