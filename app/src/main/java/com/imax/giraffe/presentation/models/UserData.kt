package com.imax.giraffe.presentation.models

data class UserData(
    val isFirstTopicCompleted: Boolean,
    val topicCompletedPercent: Int,
    val feedCount: Int,
    val levelIndex: Int,
    val feedLevel: Int
)
