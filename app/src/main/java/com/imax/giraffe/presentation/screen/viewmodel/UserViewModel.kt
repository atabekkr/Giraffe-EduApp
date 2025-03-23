package com.imax.giraffe.presentation.screen.viewmodel

import androidx.lifecycle.ViewModel
import com.imax.giraffe.presentation.utils.LocalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val localStorage: LocalStorage
): ViewModel() {

    fun getUserName() = localStorage.userName

    fun setUserName(userName: String) {
        localStorage.userName = userName
    }

}