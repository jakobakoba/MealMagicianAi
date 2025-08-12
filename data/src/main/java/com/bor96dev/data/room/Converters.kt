package com.bor96dev.data.room

import androidx.room.TypeConverter
import com.bor96dev.data.remote.dto.IngredientDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromIngredientDtoList(ingredients: List<IngredientDto>?): String ?{
        if (ingredients == null){
            return null
        }
        return gson.toJson(ingredients)
    }

    @TypeConverter
    fun toIngredientDtoList(ingredientsString: String?): List<IngredientDto>? {
        if (ingredientsString == null){
            return null
        }
        val listType = object: TypeToken<List<IngredientDto>>() {}.type
        return gson.fromJson(ingredientsString, listType)
    }
}