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

    fun getFinishedGrade() = localStorage.finishedGrade

    fun setFinishedGrade(finishedGrade: Int) {
        localStorage.finishedGrade = finishedGrade
    }

    fun getGradeId() = localStorage.gradeId

    fun setGradeId(gradeId: Int) {
        localStorage.gradeId = gradeId
    }

    fun setTopicId(id: Int) {
        localStorage.topicId = id
    }

    fun getIsAfterChooseGradeShowed() = localStorage.isAfterChooseGradeShowed
    fun setTrueToIsAfterChooseGradeShowed() {
        localStorage.isAfterChooseGradeShowed = true
    }

    fun getIsListeningExplanationShowed() = localStorage.isListeningExplanationShowed
    fun setTrueToIsListeningExplanationShowed() {
        localStorage.isListeningExplanationShowed = true
    }

    fun getIsReadingExplanationShowed() = localStorage.isReadingExplanationShowed
    fun setTrueToIsReadingExplanationShowed() {
        localStorage.isReadingExplanationShowed = true
    }

    fun getIsSpeakingExplanationShowed() = localStorage.isSpeakingExplanationShowed
    fun setTrueToIsSpeakingExplanationShowed() {
        localStorage.isSpeakingExplanationShowed = true
    }

    fun getIsWritingExplanationShowed() = localStorage.isWritingExplanationShowed
    fun setTrueToIsWritingExplanationShowed() {
        localStorage.isWritingExplanationShowed = true
    }

    fun setCompletedStatus() {
        when (localStorage.gradeId) {
            1 -> localStorage.isFirstGradeCompleted = true
            2 -> localStorage.isSecondGradeCompleted = true
            3 -> localStorage.isThirdGradeCompleted = true
            4 -> localStorage.isFourthGradeCompleted = true
        }
    }

}