package com.bor96dev.domain

import kotlinx.coroutines.flow.Flow


interface RecipeRepository {
    suspend fun findRecipesByIngredients(ingredients: String): List<Recipe>

    fun getFavoriteRecipes(): Flow<List<Recipe>>

    suspend fun addRecipeToFavorites(recipe: Recipe)

    suspend fun removeRecipeFromFavorites(recipe: Recipe)
}