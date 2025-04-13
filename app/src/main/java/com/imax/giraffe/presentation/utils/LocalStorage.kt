package com.imax.giraffe.presentation.utils

import android.content.SharedPreferences

class LocalStorage(preference: SharedPreferences) {

    var isLogin by BooleanPreference(preference, false)

    var isFirstTopicCompleted by BooleanPreference(preference, false)
    var topicCompletedPercent by IntPreference(preference, 0)
    var feedCount by IntPreference(preference, 0)
    var levelIndex by IntPreference(preference, 0)
    var feedLevel by IntPreference(preference, 1)

    var userName by StringPreference(preference, "")

    var gradeId by IntPreference(preference, 0)

    var isWritingTestCompleted by BooleanPreference(preference, false)
    var isSpeakingTestCompleted by BooleanPreference(preference, false)
    var isReadingTestCompleted by BooleanPreference(preference, false)
    var isListeningTestCompleted by BooleanPreference(preference, false)

    var isFirstGradeCompleted by BooleanPreference(preference, false)
    var isSecondGradeCompleted by BooleanPreference(preference, false)
    var isThirdGradeCompleted by BooleanPreference(preference, false)
    var isFourthGradeCompleted by BooleanPreference(preference, true)

    var isFirstGradeLocked by BooleanPreference(preference, true)
    var isSecondGradeLocked by BooleanPreference(preference, true)
    var isThirdGradeLocked by BooleanPreference(preference, true)
    var isFourthGradeLocked by BooleanPreference(preference, false)

}