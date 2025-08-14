package com.bor96dev.data.repository

import com.bor96dev.data.tflite.FoodClassifier
import com.bor96dev.domain.Classification
import com.bor96dev.domain.ClassificationRepository
import javax.inject.Inject

class ClassificationRepositoryImpl @Inject constructor(
    private val foodClassifier: FoodClassifier
) : ClassificationRepository{
    override fun classify(image: ByteArray): Classification? {
        return foodClassifier.classify(image)
    }
}