package com.bor96dev.domain

data class Recipe(
    val id: Int,
    val title: String,
    val image: String?,
    val usedIngredientCount: Int,
    val usedIngredients: List<Ingredient>,
    val missedIngredientCount: Int,
    val missedIngredients: List<Ingredient>,
    val isFavorite: Boolean
)
