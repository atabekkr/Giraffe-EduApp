package com.imax.giraffe.presentation.utils

import android.content.SharedPreferences


class LocalStorage(preference: SharedPreferences) {

    var isLogin by BooleanPreference(preference, false)

}