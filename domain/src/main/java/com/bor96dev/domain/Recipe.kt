package com.bor96dev.domain

data class Recipe(
    val id: Int,
    val title: String,
    val image: String?,
    val usedIngredients: List<Ingredient>,
    val missedIngredients: List<Ingredient>,
)
