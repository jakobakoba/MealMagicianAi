package com.bor96dev.mealmagicianai.di

import com.bor96dev.domain.RecipeRepository
import com.bor96dev.domain.usecases.FindRecipesByIngredientsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideFindRecipesByIngredientsUseCase(repository: RecipeRepository): FindRecipesByIngredientsUseCase {
        return FindRecipesByIngredientsUseCase(repository)
    }
}