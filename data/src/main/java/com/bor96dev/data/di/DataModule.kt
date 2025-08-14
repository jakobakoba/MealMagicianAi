package com.bor96dev.data.di

import android.content.Context
import androidx.room.Room
import com.bor96dev.data.remote.SpoonacularApi
import com.bor96dev.data.repository.ClassificationRepositoryImpl
import com.bor96dev.data.repository.IngredientRepositoryImpl
import com.bor96dev.data.repository.RecipeRepositoryImpl
import com.bor96dev.data.room.AppDatabase
import com.bor96dev.data.room.IngredientDao
import com.bor96dev.data.room.RecipeDao
import com.bor96dev.data.tflite.FoodClassifier
import com.bor96dev.domain.ClassificationRepository
import com.bor96dev.domain.IngredientRepository
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
    fun provideRecipeRepository(
        api: SpoonacularApi,
        dao: RecipeDao
    ): RecipeRepository {
        return RecipeRepositoryImpl(
            api = api,
            dao = dao
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "meal_magician.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(database: AppDatabase): RecipeDao {
        return database.recipeDao()
    }

    @Provides
    @Singleton
    fun provideIngredientRepository(
        dao: IngredientDao
    ): IngredientRepository {
        return IngredientRepositoryImpl(
            ingredientDao = dao
        )
    }

    @Provides
    @Singleton
    fun provideIngredientDao(database: AppDatabase): IngredientDao {
        return database.ingredientDao()
    }

    @Provides
    @Singleton
    fun provideClassificationRepository(
        foodClassifier: FoodClassifier
    ) : ClassificationRepository {
        return ClassificationRepositoryImpl(
            foodClassifier = foodClassifier
        )
    }



}