package com.bor96dev.domain

interface ClassificationRepository {
    fun classify(image: ByteArray) : Classification?
}