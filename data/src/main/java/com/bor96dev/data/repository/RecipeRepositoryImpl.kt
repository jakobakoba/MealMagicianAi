package com.bor96dev.data.repository

import com.bor96dev.data.mappers.toDomain
import com.bor96dev.data.mappers.toEntity
import com.bor96dev.data.remote.SpoonacularApi
import com.bor96dev.data.room.RecipeDao
import com.bor96dev.domain.Recipe
import com.bor96dev.domain.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RecipeRepositoryImpl (
    private val api: SpoonacularApi,
    private val dao: RecipeDao
): RecipeRepository {
    override suspend fun findRecipesByIngredients(ingredients: String): Flow<List<Recipe>> {
        val favoriteIdsFlow = dao.getAllFavoriteIds()

        val networkRecipesFlow = flow {
            val recipesDto = api.findRecipesByIngredients(
                apiKey = "",
                ingredients = ingredients
            )
            emit(recipesDto.map{it.toDomain()})
        }

        return combine(networkRecipesFlow, favoriteIdsFlow){networkRecipes, favoriteIds ->
            networkRecipes.map{recipe ->
                recipe.copy(isFavorite = recipe.id in favoriteIds)
            }
        }
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
