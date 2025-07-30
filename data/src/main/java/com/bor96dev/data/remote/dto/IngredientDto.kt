package com.bor96dev.data.remote.dto

import com.google.gson.annotations.SerializedName

data class IngredientDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("unit")
    val unit: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String?,
)