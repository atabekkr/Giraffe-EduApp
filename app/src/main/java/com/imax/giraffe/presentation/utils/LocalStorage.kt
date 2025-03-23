package com.imax.giraffe.presentation.utils

import android.content.SharedPreferences


class LocalStorage(preference: SharedPreferences) {

    var isLogin by BooleanPreference(preference, false)

    var isFirstTopicCompleted by BooleanPreference(preference, false)

    var topicCompletedPercent by IntPreference(preference, 0)

    var feedCount by IntPreference(preference, 0)

    var levelIndex by IntPreference(preference, 0)

    var userName by StringPreference(preference, "")

    var gradeId by IntPreference(preference, 0)

}