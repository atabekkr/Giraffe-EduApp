package com.imax.giraffe.presentation.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imax.giraffe.presentation.data.db.entities.Grade
import com.imax.giraffe.presentation.data.db.entities.GradeTopic
import com.imax.giraffe.presentation.data.db.entities.Levels
import com.imax.giraffe.presentation.data.db.entities.Listening
import com.imax.giraffe.presentation.data.db.entities.Reading
import com.imax.giraffe.presentation.data.db.entities.Speaking
import com.imax.giraffe.presentation.data.db.entities.TopicOverview
import com.imax.giraffe.presentation.data.db.entities.Vocabulary
import com.imax.giraffe.presentation.data.db.entities.Writing
import com.imax.giraffe.presentation.data.repo.MainRepository
import com.imax.giraffe.presentation.utils.LocalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val localStorage: LocalStorage
) : ViewModel() {

    private val _getGradesState = MutableStateFlow<List<Grade>?>(null)
    val getGradesState: StateFlow<List<Grade>?> = _getGradesState

    fun getGrades() {
        viewModelScope.launch {
            _getGradesState.value = repository.getGrades()
        }
    }

    val getGradeResult = MutableStateFlow<Grade?>(null)
    suspend fun getGrade() {
        getGradeResult.emit(repository.getGrade())
    }

    val getGradeTopicResult = MutableStateFlow<GradeTopic?>(null)
    suspend fun getGradeTopics() {
        getGradeTopicResult.value = repository.getGradeTopics()
    }

    val getGradeLevelsResult = MutableStateFlow<Levels?>(null)
    suspend fun getGradeLevels() {
        getGradeLevelsResult.value = repository.getGradeLevels()
    }

    private val _getListeningTestsResult = MutableStateFlow<List<Listening>?>(null)
    val getListeningTestsResult: StateFlow<List<Listening>?> = _getListeningTestsResult
    fun getListeningTests() {
        viewModelScope.launch {
            _getListeningTestsResult.value = repository.getListeningTests()
        }
    }

    private val _getSpeakingTestsResult = MutableStateFlow<List<Speaking>?>(null)
    val getSpeakingTestsResult: StateFlow<List<Speaking>?> = _getSpeakingTestsResult
    fun getSpeakingTests() {
        viewModelScope.launch {
            _getSpeakingTestsResult.value = repository.getSpeakingTests()
        }
    }

    private val _getWritingTestsResult = MutableStateFlow<List<Writing>?>(null)
    val getWritingTestsResult: StateFlow<List<Writing>?> = _getWritingTestsResult
    fun getWritingTests() {
        viewModelScope.launch {
            _getWritingTestsResult.value = repository.getWritingTests()
        }
    }

    private val _getReadingTestsResult = MutableStateFlow<List<Reading>?>(null)
    val getReadingTestsResult: StateFlow<List<Reading>?> = _getReadingTestsResult
    fun getReadingTests() {
        viewModelScope.launch(Dispatchers.IO) {
            _getReadingTestsResult.value = repository.getReadingTests()
        }
    }

    private val _getVocabularyResult = MutableStateFlow<Vocabulary?>(null)
    val getVocabularyResult: StateFlow<Vocabulary?> = _getVocabularyResult
    fun getVocabulary() {
        viewModelScope.launch {
            _getVocabularyResult.value = repository.getVocabulary()
        }
    }

    private val _getTopicContentResult = MutableStateFlow<TopicOverview?>(null)
    val getTopicContentResult: StateFlow<TopicOverview?> = _getTopicContentResult
    fun getTopicContent() {
        viewModelScope.launch {
            _getTopicContentResult.value = repository.getTopicContent()
        }
    }

}