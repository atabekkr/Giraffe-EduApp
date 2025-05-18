package com.imax.giraffe.presentation.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GradeCompletionData")
data class GradeCompletionData(
    @PrimaryKey
    val grade_id: Int,
    val first_topic_completed_percent: Int,
    val second_topic_completed_percent: Int,
    val feed_count: Int,
    val level: Int,
    val isWritingTestCompleted: Boolean,
    val isSpeakingTestCompleted: Boolean,
    val isReadingTestCompleted: Boolean,
    val isListeningTestCompleted: Boolean
)
