package com.bor96dev.domain.usecases.camera

import com.bor96dev.domain.Classification
import com.bor96dev.domain.ClassificationRepository
import javax.inject.Inject

class ClassifyImageUseCase @Inject constructor(
    private val repository: ClassificationRepository
){
    operator fun invoke(image: ByteArray): Classification? {
        return repository.classify(image)
    }
}