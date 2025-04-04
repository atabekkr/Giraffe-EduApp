package com.imax.giraffe.presentation.screen.viewmodel

import androidx.lifecycle.ViewModel
import com.imax.giraffe.presentation.models.VoiceToTextParserState
import com.imax.giraffe.presentation.utils.VoiceToTextParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class VoiceViewModel @Inject constructor(private val voiceToTextParser: VoiceToTextParser) :
    ViewModel() {

    val state: StateFlow<VoiceToTextParserState> = voiceToTextParser.state

    fun startListening(languageCode: String = "en") {
        voiceToTextParser.startListening(languageCode)
    }

    fun stopListening() {
        voiceToTextParser.stopListening()
    }

    fun setDefaultText() {
        voiceToTextParser.setDefaultText()
    }
}
