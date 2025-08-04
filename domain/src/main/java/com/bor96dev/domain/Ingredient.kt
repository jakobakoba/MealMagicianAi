package com.bor96dev.domain

data class Ingredient(
    val id: Int,
    val name: String,
    val amount: Double,
    val unit: String?,
    val image: String?,
)