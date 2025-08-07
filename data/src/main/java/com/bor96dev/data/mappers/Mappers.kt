package com.bor96dev.data.mappers

import com.bor96dev.data.remote.dto.IngredientDto
import com.bor96dev.data.remote.dto.RecipeDto
import com.bor96dev.data.room.RecipeEntity
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
        usedIngredientCount = this.usedIngredientCount,
        usedIngredients = this.usedIngredients.map { it.toDomain() },
        missedIngredientCount = this.missedIngredientCount,
        missedIngredients = this.missedIngredients.map { it.toDomain() },
        isFavorite = false
    )
}

fun Recipe.toEntity(): RecipeEntity{
    return RecipeEntity(
        id = this.id,
        title = this.title,
        image = this.image ?: "",
        usedIngredientCount = this.usedIngredientCount,
        usedIngredients = this.usedIngredients.map{it.toDto()},
        missedIngredientCount = this.missedIngredientCount,
        missedIngredients = this.missedIngredients.map{it.toDto()}
    )
}

fun RecipeEntity.toDomain(): Recipe {
    return Recipe(
        id = this.id,
        title = this.title,
        image = this.image,
        usedIngredientCount = this.usedIngredientCount,
        usedIngredients = this.usedIngredients.map{it.toDomain()},
        missedIngredientCount = this.missedIngredientCount,
        missedIngredients = this.missedIngredients.map{it.toDomain()},
        isFavorite = true
    )
}

fun Ingredient.toDto(): IngredientDto {
    return IngredientDto (
        id = this.id,
        name = this.name,
        amount = this.amount,
        unit = this.unit,
        image = this.image
    )
}