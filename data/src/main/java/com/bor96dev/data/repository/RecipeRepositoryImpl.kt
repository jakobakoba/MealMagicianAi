package com.bor96dev.data.repository

import com.bor96dev.data.mappers.toDomain
import com.bor96dev.data.mappers.toEntity
import com.bor96dev.data.remote.SpoonacularApi
import com.bor96dev.data.room.RecipeDao
import com.bor96dev.domain.Recipe
import com.bor96dev.domain.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeRepositoryImpl (
    private val api: SpoonacularApi,
    private val dao: RecipeDao
): RecipeRepository {
    override suspend fun findRecipesByIngredients(ingredients: String): List<Recipe> {
        val recipesDto = api.findRecipesByIngredients(
            apiKey = "e6abf47d0d0a4d2f874cbe0b76a89210",
            ingredients = ingredients
        )
        return recipesDto.map{it.toDomain()}
    }

    override fun getFavoriteRecipes(): Flow<List<Recipe>> {
        return dao.getAll().map{entities ->
            entities.map{it.toDomain()}
        }
    }

    override suspend fun addRecipeToFavorites(recipe: Recipe) {
        dao.insert(recipe.toEntity())
    }

    override suspend fun removeRecipeFromFavorites(recipe: Recipe) {
        dao.delete(recipe.toEntity())
    }
}