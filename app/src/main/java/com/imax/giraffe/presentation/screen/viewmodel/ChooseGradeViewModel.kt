package com.imax.giraffe.presentation.screen.viewmodel

import androidx.lifecycle.ViewModel
import com.imax.giraffe.presentation.utils.LocalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChooseGradeViewModel @Inject constructor(
    private val localStorage: LocalStorage
) : ViewModel() {

    fun getIsFirstGradeLocked() = localStorage.isFirstGradeLocked
    fun setIsFirstGradeLocked(value: Boolean) {
        localStorage.isFirstGradeLocked = value
    }

    fun getIsSecondGradeLocked() = localStorage.isSecondGradeLocked
    fun setIsSecondGradeLocked(value: Boolean) {
        localStorage.isSecondGradeLocked = value
    }

    fun getIsThirdGradeLocked() = localStorage.isThirdGradeLocked
    fun setIsThirdGradeLocked(value: Boolean) {
        localStorage.isThirdGradeLocked = value
    }

    fun getIsFourthGradeLocked() = localStorage.isFourthGradeLocked
    fun setIsFourthGradeLocked(value: Boolean) {
        localStorage.isFourthGradeLocked = value
    }

}