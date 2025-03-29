package com.imax.giraffe.presentation.screen.viewmodel

import androidx.lifecycle.ViewModel
import com.imax.giraffe.presentation.utils.LocalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val localStorage: LocalStorage
) : ViewModel() {

    fun isLogin(): Boolean {
        return localStorage.isLogin
    }

    fun setLogin(isLogin: Boolean) {
        localStorage.isLogin = isLogin
    }
}