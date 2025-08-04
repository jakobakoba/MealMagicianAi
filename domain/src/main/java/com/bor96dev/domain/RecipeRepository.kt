package com.bor96dev.domain

interface RecipeRepository {
    suspend fun findRecipesByIngredients(ingredients: String): List<Recipe>
}