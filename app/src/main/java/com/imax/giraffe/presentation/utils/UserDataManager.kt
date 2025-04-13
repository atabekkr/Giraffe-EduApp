package com.imax.giraffe.presentation.utils

import com.imax.giraffe.presentation.models.UserData
import javax.inject.Inject

class UserDataManager @Inject constructor(private val localStorage: LocalStorage) {

    fun getLocalData(gradeId: Int): UserData {
        return when (gradeId) {
            1 -> {
                UserData(
                    isFirstTopicCompleted = localStorage.isFirstTopicCompleted,
                    topicCompletedPercent = localStorage.topicCompletedPercent,
                    feedCount = localStorage.feedCount,
                    levelIndex = localStorage.levelIndex,
                    feedLevel = localStorage.feedLevel
                )
            }

            2 -> {
                UserData(
                    isFirstTopicCompleted = localStorage.isFirstTopicCompleted,
                    topicCompletedPercent = localStorage.topicCompletedPercent,
                    feedCount = localStorage.feedCount,
                    levelIndex = localStorage.levelIndex,
                    feedLevel = localStorage.feedLevel
                )
            }

            3 -> {
                UserData(
                    isFirstTopicCompleted = localStorage.isFirstTopicCompleted,
                    topicCompletedPercent = localStorage.topicCompletedPercent,
                    feedCount = localStorage.feedCount,
                    levelIndex = localStorage.levelIndex,
                    feedLevel = localStorage.feedLevel
                )
            }

            else -> {
                UserData(
                    isFirstTopicCompleted = localStorage.isFirstTopicCompleted,
                    topicCompletedPercent = localStorage.topicCompletedPercent,
                    feedCount = localStorage.feedCount,
                    levelIndex = localStorage.levelIndex,
                    feedLevel = localStorage.feedLevel
                )
            }
        }
    }

    fun setLocalData(
        isFirstTopicCompleted: Boolean
    ) {
        when (localStorage.gradeId) {
            1 -> {
               localStorage.isFirstTopicCompleted = isFirstTopicCompleted
            }

            2 -> {
                UserData(
                    isFirstTopicCompleted = localStorage.isFirstTopicCompleted,
                    topicCompletedPercent = localStorage.topicCompletedPercent,
                    feedCount = localStorage.feedCount,
                    levelIndex = localStorage.levelIndex,
                    feedLevel = localStorage.feedLevel
                )
            }

            3 -> {
                UserData(
                    isFirstTopicCompleted = localStorage.isFirstTopicCompleted,
                    topicCompletedPercent = localStorage.topicCompletedPercent,
                    feedCount = localStorage.feedCount,
                    levelIndex = localStorage.levelIndex,
                    feedLevel = localStorage.feedLevel
                )
            }

            else -> {
                UserData(
                    isFirstTopicCompleted = localStorage.isFirstTopicCompleted,
                    topicCompletedPercent = localStorage.topicCompletedPercent,
                    feedCount = localStorage.feedCount,
                    levelIndex = localStorage.levelIndex,
                    feedLevel = localStorage.feedLevel
                )
            }
        }
    }

}