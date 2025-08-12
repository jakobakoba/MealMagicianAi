package com.bor96dev.data.repository

import com.bor96dev.data.mappers.toDomain
import com.bor96dev.data.mappers.toEntity
import com.bor96dev.data.room.IngredientDao
import com.bor96dev.domain.Ingredient
import com.bor96dev.domain.IngredientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class IngredientRepositoryImpl(
    private val ingredientDao: IngredientDao
) : IngredientRepository {
    override fun getIngredients(): Flow<List<Ingredient>> {
        return ingredientDao.getAllIngredients().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addIngredient(ingredient: Ingredient) {
        ingredientDao.insertIngredient(ingredient.toEntity())
    }

    override suspend fun deleteIngredient(ingredient: Ingredient) {
        ingredientDao.deleteIngredient(ingredient.toEntity())
    }
}