package com.bor96dev.data.tflite

import android.content.Context
import android.graphics.BitmapFactory
import com.bor96dev.domain.Classification
import com.bor96dev.domain.ClassificationRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodClassifier @Inject constructor(
    @ApplicationContext private val context: Context
) : ClassificationRepository {

    private fun loadModelFile(): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd("ingredients.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        return fileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            fileDescriptor.startOffset,
            fileDescriptor.declaredLength
        )
    }

    private val interpreter: Interpreter by lazy {
        Interpreter(loadModelFile())

    }
    private val labels: List<String> by lazy {
        context.assets.open("labels.txt").bufferedReader().readLines()
    }

    private val imageProcessor = ImageProcessor.Builder()
        .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
        .add(NormalizeOp(127.5f, 127.5f))
        .build()

    override fun classify(image: ByteArray): Classification? {
        val bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
        val tensorImage = imageProcessor.process(TensorImage.fromBitmap(bitmap))
        val output = Array(1) { FloatArray(labels.size) }

        interpreter.run(tensorImage.buffer, output)

        val scores = output[0]
        val maxIndex = scores.indices.maxByOrNull { scores[it] } ?: return null

        val maxScore = scores[maxIndex]
        if (maxScore < 0.75f) return null

        return Classification(
            label = labels.getOrElse(maxIndex) { "Unknown" },
            score = maxScore
        )
    }


}