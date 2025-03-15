package com.imax.giraffe.presentation.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imax.giraffe.presentation.data.db.entities.Grade
import com.imax.giraffe.presentation.data.db.entities.Listening
import com.imax.giraffe.presentation.data.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _getGradesState = MutableStateFlow<List<Grade>?>(null)
    val getGradesState: StateFlow<List<Grade>?> = _getGradesState

    fun getGrades() {
        viewModelScope.launch {
            _getGradesState.value = repository.getGrades()
        }
    }

    private val _getListeningTestsResult = MutableStateFlow<List<Listening>?>(null)
    val getListeningTestsResult: StateFlow<List<Listening>?> = _getListeningTestsResult
    fun getListeningTests(gradeId: Int, levelId: Int) {
        viewModelScope.launch {
            _getListeningTestsResult.value = repository.getListeningTests(gradeId, levelId)
        }
    }

}