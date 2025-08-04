package com.bor96dev.data.mappers

import com.bor96dev.data.remote.dto.IngredientDto
import com.bor96dev.data.remote.dto.RecipeDto
import com.bor96dev.domain.Ingredient
import com.bor96dev.domain.Recipe

fun IngredientDto.toDomain(): Ingredient {
    return Ingredient(
        id = this.id,
        name = this.name,
        amount = this.amount,
        unit = this.unit ?: "",
        image = this.image ?: ""
    )
}

fun RecipeDto.toDomain(): Recipe {
    return Recipe(
        id = this.id,
        title = this.title,
        image = this.image ?: "",
        usedIngredients = this.usedIngredients.map { it.toDomain() },
        missedIngredients = this.missedIngredients.map { it.toDomain() }
    )
}