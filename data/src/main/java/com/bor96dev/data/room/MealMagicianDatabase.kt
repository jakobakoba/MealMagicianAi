package com.bor96dev.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RecipeEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MealMagicianDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}