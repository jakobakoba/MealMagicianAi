package com.bor96dev.domain.usecases.ingredients

import com.bor96dev.domain.Ingredient
import com.bor96dev.domain.IngredientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(
    private val repository: IngredientRepository
){

    operator fun invoke() : Flow<List<Ingredient>> {
        return repository.getIngredients()
    }
}