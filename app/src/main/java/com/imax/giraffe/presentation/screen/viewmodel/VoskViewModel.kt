package com.imax.giraffe.presentation.screen.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.vosk.Model
import org.vosk.Recognizer
import org.vosk.android.RecognitionListener
import org.vosk.android.SpeechService
import org.vosk.android.StorageService

class VoskViewModel : ViewModel(), RecognitionListener {
    var resultText by mutableStateOf("")
        private set

    private var model: Model? = null
    private var speechService: SpeechService? = null

     fun initModel(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                StorageService.unpack(context, "model-en-us", "model",
                    { loadedModel ->
                        model = loadedModel
                    },
                    { e -> resultText = "Failed to unpack the model: ${e.message}" }
                )
            } catch (e: Exception) {
                resultText = "Model initialization failed: ${e.message}"
            }
        }
    }

    fun recognizeMicrophone() {
        val rec = model?.let { Recognizer(it, 16000.0f) } ?: return
        speechService = SpeechService(rec, 16000.0f)
        speechService?.startListening(this) // Передаем `this` как RecognitionListener
    }

    fun stopRecognition() {
        speechService?.stop()
        speechService = null
    }

    override fun onResult(hypothesis: String?) {
        resultText = hypothesis ?: ""
    }

    override fun onFinalResult(hypothesis: String?) {
        resultText = hypothesis ?: ""
        stopRecognition()
    }

    override fun onPartialResult(hypothesis: String?) {
        resultText = hypothesis ?: ""
    }

    override fun onError(e: Exception?) {
        resultText = "Error: ${e?.message}"
    }

    override fun onTimeout() {
        stopRecognition()
    }
}

