package com.bor96dev.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bor96dev.data.remote.dto.IngredientDto

@Entity(tableName = "favorite_recipes")
data class RecipeEntity (
    @PrimaryKey val id: Int,
    val title: String,
    val image: String,
    val usedIngredientCount: Int,
    val usedIngredients: List<IngredientDto>,
    val missedIngredientCount: Int,
    val missedIngredients: List<IngredientDto>
)