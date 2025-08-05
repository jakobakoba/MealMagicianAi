package com.bor96dev.data.di

import android.content.Context
import androidx.room.Room
import com.bor96dev.data.remote.SpoonacularApi
import com.bor96dev.data.repository.RecipeRepositoryImpl
import com.bor96dev.data.room.MealMagicianDatabase
import com.bor96dev.data.room.RecipeDao
import com.bor96dev.domain.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val BASE_URL = "https://api.spoonacular.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSpoonacularApi(retrofit: Retrofit) : SpoonacularApi {
        return retrofit.create(SpoonacularApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(api: SpoonacularApi): RecipeRepository {
        return RecipeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MealMagicianDatabase {
        return Room.databaseBuilder(
            context,
            MealMagicianDatabase::class.java,
            "meal_magician.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(database: MealMagicianDatabase): RecipeDao {
        return database.recipeDao()
    }


}