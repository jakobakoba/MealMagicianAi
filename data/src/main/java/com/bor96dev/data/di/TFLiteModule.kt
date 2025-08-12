package com.bor96dev.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.tensorflow.lite.task.vision.classifier.ImageClassifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TFLiteModule {

    private const val MODEL_FILE_NAME = "ingredients.tflite"

    @Provides
    @Singleton
    fun provideImageClassifier (
        @ApplicationContext context: Context
    ): ImageClassifier {

        val options = ImageClassifier.ImageClassifierOptions.builder()
            .setScoreThreshold(0.8f)
            .setMaxResults(1)
            .build()

        return ImageClassifier.createFromFileAndOptions(
            context,
            MODEL_FILE_NAME,
            options
        )
    }
}