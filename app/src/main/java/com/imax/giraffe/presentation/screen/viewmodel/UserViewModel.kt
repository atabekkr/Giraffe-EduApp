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
        localStorage.topicCompletedPercent += 20
        if (localStorage.topicCompletedPercent == 100) {
            resetCompletedStatus()
            setFirstTopicCompleted()
        }
    }

    private fun setFirstTopicCompleted() {
        localStorage.isFirstTopicCompleted = true
        localStorage.topicCompletedPercent = 0
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

    fun isWritingTestCompleted() = localStorage.isWritingTestCompleted
    fun setWritingTestCompleted() {
        localStorage.isWritingTestCompleted = true
    }

    fun isSpeakingTestCompleted() = localStorage.isSpeakingTestCompleted
    fun setSpeakingTestCompleted() {
        localStorage.isSpeakingTestCompleted = true
    }

    fun isReadingTestCompleted() = localStorage.isReadingTestCompleted
    fun setReadingTestCompleted() {
        localStorage.isReadingTestCompleted = true
    }

    fun isListeningTestCompleted() = localStorage.isListeningTestCompleted
    fun setListeningTestCompleted() {
        localStorage.isListeningTestCompleted = true
    }

    fun getFeedLevel() = localStorage.feedLevel
    fun setFeedLevel(level: Int) {
        localStorage.feedLevel = level
    }

    private fun resetCompletedStatus() {
        localStorage.isWritingTestCompleted = false
        localStorage.isSpeakingTestCompleted = false
        localStorage.isReadingTestCompleted = false
        localStorage.isListeningTestCompleted = false
    }

}