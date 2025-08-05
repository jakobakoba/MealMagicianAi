package com.bor96dev.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDao {
    @Query("SELECT * FROM favorite_recipes")
    fun getAll(): Flow<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: RecipeEntity)

    @Delete
    suspend fun delete(recipe: RecipeEntity)

}