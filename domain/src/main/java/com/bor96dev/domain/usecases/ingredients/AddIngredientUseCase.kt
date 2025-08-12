package com.bor96dev.domain.usecases.ingredients

import com.bor96dev.domain.Ingredient
import com.bor96dev.domain.IngredientRepository
import javax.inject.Inject

class AddIngredientUseCase @Inject constructor(
    private val repository: IngredientRepository
){
    suspend operator fun invoke(ingredient : Ingredient){
        repository.addIngredient(ingredient)
    }
}