package com.bor96dev.domain.usecases

import com.bor96dev.domain.Recipe
import com.bor96dev.domain.RecipeRepository
import javax.inject.Inject

class FindRecipesByIngredientsUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(ingredients: String): List<Recipe> {
        if (ingredients.isBlank()) {
            return emptyList()
        }
        return repository.findRecipesByIngredients(ingredients)
    }
}