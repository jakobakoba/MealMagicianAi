package com.bor96dev.domain

import kotlinx.coroutines.flow.Flow

interface IngredientRepository {

    fun getIngredients(): Flow<List<Ingredient>>

    suspend fun addIngredient(ingredient: Ingredient)

    suspend fun deleteIngredient(ingredient: Ingredient)
}