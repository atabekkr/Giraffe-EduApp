package com.imax.giraffe.presentation.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imax.giraffe.presentation.data.db.entities.GradeCompletionData
import com.imax.giraffe.presentation.data.repo.GradeCompletionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GradeDataViewModel @Inject constructor(
    private val repository: GradeCompletionRepository
) : ViewModel() {
    val getGradeDataResult = MutableStateFlow<GradeCompletionData?>(null)
    fun getGrade() {
        viewModelScope.launch {
            getGradeDataResult.emit(repository.getGradeData())
        }
    }

    fun incrementFeedCount() {
        viewModelScope.launch {
            repository.incrementFeedCount()
        }
    }

    fun resetFeedCount() {
        viewModelScope.launch {
            repository.resetFeedCount()
        }
    }

    fun incrementLevel(levelCount: Int) {
        viewModelScope.launch {
            repository.incrementLevel(levelCount)
        }
    }

    fun incrementTopicCompletedPercent() {
        viewModelScope.launch {
            repository.incrementTopicCompletedPercent()
        }
    }

    fun updateListeningTestCompleted() {
        viewModelScope.launch {
            repository.updateListeningTestCompleted()
        }
    }

    fun updateSpeakingTestCompleted() {
        viewModelScope.launch {
            repository.updateSpeakingTestCompleted()
        }
    }

    fun updateWritingTestCompleted() {
        viewModelScope.launch {
            repository.updateWritingTestCompleted()
        }
    }

    fun updateReadingTestCompleted() {
        viewModelScope.launch {
            repository.updateReadingTestCompleted()
        }
    }

}