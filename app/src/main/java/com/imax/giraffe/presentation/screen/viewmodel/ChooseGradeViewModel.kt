package com.imax.giraffe.presentation.screen.viewmodel

import androidx.lifecycle.ViewModel
import com.imax.giraffe.presentation.utils.LocalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChooseGradeViewModel @Inject constructor(
    private val localStorage: LocalStorage
) : ViewModel() {

    fun getIsFirstGradeCompleted() = localStorage.isFirstGradeCompleted
    fun setIsFirstGradeCompleted(value: Boolean) {
        localStorage.isFirstGradeCompleted = value
    }

    fun getIsSecondGradeCompleted() = localStorage.isSecondGradeCompleted
    fun setIsSecondGradeCompleted(value: Boolean) {
        localStorage.isSecondGradeCompleted = value
    }

    fun getIsThirdGradeCompleted() = localStorage.isThirdGradeCompleted
    fun setIsThirdGradeCompleted(value: Boolean) {
        localStorage.isThirdGradeCompleted = value
    }

    fun getIsFourthGradeCompleted() = localStorage.isFourthGradeCompleted
    fun setIsFourthGradeCompleted(value: Boolean) {
        localStorage.isFourthGradeCompleted = value
    }

}