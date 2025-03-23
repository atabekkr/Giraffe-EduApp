package com.imax.giraffe.presentation.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imax.giraffe.presentation.data.db.entities.Grade
import com.imax.giraffe.presentation.data.db.entities.GradeTopic
import com.imax.giraffe.presentation.data.db.entities.Levels
import com.imax.giraffe.presentation.data.db.entities.Listening
import com.imax.giraffe.presentation.data.db.entities.Reading
import com.imax.giraffe.presentation.data.db.entities.Vocabulary
import com.imax.giraffe.presentation.data.db.entities.Writing
import com.imax.giraffe.presentation.data.repo.MainRepository
import com.imax.giraffe.presentation.utils.LocalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
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
    suspend fun getGrade(gradeId: Int) {
        getGradeResult.emit(repository.getGrade(gradeId))
    }

    val getGradeTopicResult = MutableStateFlow<GradeTopic?>(null)
    suspend fun getGradeTopics(gradeId: Int) {
        getGradeTopicResult.value = repository.getGradeTopics(gradeId)
    }

    val getGradeLevelsResult = MutableStateFlow<Levels?>(null)
    suspend fun getGradeLevels(gradeId: Int) {
        getGradeLevelsResult.value = repository.getGradeLevels(gradeId)
    }

    private val _getListeningTestsResult = MutableStateFlow<List<Listening>?>(null)
    val getListeningTestsResult: StateFlow<List<Listening>?> = _getListeningTestsResult
    fun getListeningTests(gradeId: Int, levelId: Int) {
        viewModelScope.launch {
            _getListeningTestsResult.value = repository.getListeningTests(gradeId, levelId)
        }
    }

    private val _getWritingTestsResult = MutableStateFlow<List<Writing>?>(null)
    val getWritingTestsResult: StateFlow<List<Writing>?> = _getWritingTestsResult
    fun getWritingTests(gradeId: Int, levelId: Int) {
        viewModelScope.launch {
            _getWritingTestsResult.value = repository.getWritingTests(gradeId, levelId)
        }
    }

    private val _getReadingTestsResult = MutableStateFlow<List<Reading>?>(null)
    val getReadingTestsResult: StateFlow<List<Reading>?> = _getReadingTestsResult
    fun getReadingTests(gradeId: Int, levelId: Int) {
        viewModelScope.launch {
            _getReadingTestsResult.value = repository.getReadingTests(gradeId, levelId)
        }
    }

    private val _getVocabularyResult = MutableStateFlow<Vocabulary?>(null)
    val getVocabularyResult: StateFlow<Vocabulary?> = _getVocabularyResult
    fun getVocabulary(gradeId: Int, levelId: Int) {
        viewModelScope.launch {
            _getVocabularyResult.value = repository.getVocabulary(gradeId, levelId)
        }
    }

    fun setFirstTopicCompleted() {
        localStorage.isFirstTopicCompleted = true
    }

    fun isFirstTopicCompleted() = localStorage.isFirstTopicCompleted

    fun getTopicCompletedPercent() = localStorage.topicCompletedPercent

    fun getFeedCount() = localStorage.feedCount

}