package com.bor96dev.data.di

import android.content.Context
import com.bor96dev.data.tflite.FoodClassifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TFLiteModule {

    @Provides
    @Singleton
    fun provideFoodClassifier(
        @ApplicationContext context: Context
    ): FoodClassifier {
        return FoodClassifier(context)
    }




}