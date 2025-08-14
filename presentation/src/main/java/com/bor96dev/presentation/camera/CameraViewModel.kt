package com.bor96dev.presentation.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bor96dev.domain.Classification
import com.bor96dev.domain.usecases.camera.ClassifyImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val classifyImageUseCase: ClassifyImageUseCase
) : ViewModel() {

    data class CameraUiState(
        val classification: Classification? = null,
        val error: String? = null
    )

    private val _uiState = MutableStateFlow(CameraUiState())
    val uiState = _uiState.asStateFlow()

    private var lastAnalysisTimestamp = 0L

    fun onAnalyzeImage(image: ByteArray){
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastAnalysisTimestamp < 1000) return
        lastAnalysisTimestamp = currentTime

        viewModelScope.launch {
            val result = classifyImageUseCase(image)
            _uiState.update {currentState ->
                if (result == null){
                    currentState.copy(classification = result, error = null)
                } else {
                    currentState.copy(classification = null, error = "Не удалось распознать продукт")
                }
            }
        }
    }
}