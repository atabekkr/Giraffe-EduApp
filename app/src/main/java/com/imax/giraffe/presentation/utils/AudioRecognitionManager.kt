package com.imax.giraffe.presentation.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import java.io.File
import java.io.FileOutputStream

class AudioRecognitionManager(private val context: Context) {
    private var speechRecognizer: SpeechRecognizer? = null
    private var audioFile: File? = null
    private var fileOutputStream: FileOutputStream? = null
    private var isRecording = false
    
    // Create and prepare the speech recognizer
    fun initialize(outputFile: File) {
        audioFile = outputFile
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        speechRecognizer?.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {
                // Save audio buffer to file
                buffer?.let {
                    fileOutputStream?.write(it)
                }
            }
            override fun onEndOfSpeech() {}
            override fun onError(error: Int) {}
            override fun onResults(results: Bundle?) {
                // Handle speech recognition results
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                // Do something with the recognized text
            }
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }
    
    // Start recording and recognition
    fun start(language: String = "en") {
        try {
            fileOutputStream = FileOutputStream(audioFile)
            isRecording = true
            
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, language)
                putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
                // This is crucial - get access to raw audio data
                putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.packageName)
                putExtra("android.speech.extra.GET_AUDIO_FORMAT", "audio/AMR")
                putExtra("android.speech.extra.GET_AUDIO", true)
            }
            
            speechRecognizer?.startListening(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    // Stop recording and recognition
    fun stop() {
        isRecording = false
        speechRecognizer?.stopListening()
        fileOutputStream?.close()
        fileOutputStream = null
    }
    
    // Release resources
    fun release() {
        speechRecognizer?.destroy()
        speechRecognizer = null
    }
}