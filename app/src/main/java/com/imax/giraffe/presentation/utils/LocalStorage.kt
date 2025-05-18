package com.imax.giraffe.presentation.utils

import android.content.SharedPreferences

class LocalStorage(preference: SharedPreferences) {

    var isLogin by BooleanPreference(preference, false)

    var userName by StringPreference(preference, "")

    var gradeId by IntPreference(preference, 1)
    var topicId by IntPreference(preference, 1)

    var isFirstGradeCompleted by BooleanPreference(preference, false)
    var isSecondGradeCompleted by BooleanPreference(preference, false)
    var isThirdGradeCompleted by BooleanPreference(preference, false)
    var isFourthGradeCompleted by BooleanPreference(preference, false)

    var isListeningExplanationShowed by BooleanPreference(preference, false)
    var isReadingExplanationShowed by BooleanPreference(preference, false)
    var isWritingExplanationShowed by BooleanPreference(preference, false)
    var isSpeakingExplanationShowed by BooleanPreference(preference, false)

    var isAfterChooseGradeShowed by BooleanPreference(preference, false)

}