package com.bor96dev.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {

    @GET("recipes/findByIngredients")
    suspend fun findRecipesByIngredients(
        @Query("apiKey")
        apiKey: String,
        @Query("ingredients")
        ingredients: String,
        @Query("number")
        number: Int = 10,
        @Query("ranking")
        ranking: Int = 1,
        @Query("ignorePantry")
        ignorePantry:Boolean = true
    )
}