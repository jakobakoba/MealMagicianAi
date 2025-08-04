package com.bor96dev.data.repository

import com.bor96dev.data.remote.SpoonacularApi
import com.bor96dev.domain.Recipe
import com.bor96dev.domain.RecipeRepository

class RecipeRepositoryImpl (
    private val api: SpoonacularApi
): RecipeRepository {
    override suspend fun findRecipesByIngredients(ingredients: String): List<Recipe> {
        val recipesDto = api.findRecipesByIngredients(
            apiKey = "e6abf47d0d0a4d2f874cbe0b76a89210",
            ingredients = ingredients
        )

        return emptyList()
    }
}