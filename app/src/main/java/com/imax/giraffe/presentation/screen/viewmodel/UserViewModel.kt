package com.imax.giraffe.presentation.screen.viewmodel

import androidx.lifecycle.ViewModel
import com.imax.giraffe.presentation.utils.LocalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val localStorage: LocalStorage
) : ViewModel() {

    fun getUserName() = localStorage.userName

    fun setUserName(userName: String) {
        localStorage.userName = userName
    }

    fun getGradeId() = localStorage.gradeId

    fun setGradeId(gradeId: Int) {
        localStorage.gradeId = gradeId
    }

    fun getLevelIndex() = localStorage.levelIndex

    fun incrementLevelIndex() {
        localStorage.levelIndex++
        localStorage.feedCount++
        localStorage.topicCompletedPercent += 10
        if (localStorage.topicCompletedPercent == 100) setFirstTopicCompleted()
    }

    fun setFirstTopicCompleted() {
        localStorage.isFirstTopicCompleted = true
    }

    fun getTopicId(): Int {
        return if (!localStorage.isFirstTopicCompleted) 1 else 2
    }

    fun isFirstTopicCompleted() = localStorage.isFirstTopicCompleted

    fun getTopicCompletedPercent() = localStorage.topicCompletedPercent

    fun getFeedCount() = localStorage.feedCount

    fun resetFeedCount() {
        localStorage.feedCount = 0
    }

}