package com.imax.giraffe.presentation.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TopicOverview")
data class TopicOverview(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("grade_id")
    val gradeId: Int,
    @ColumnInfo(name = "topic_id")
    val topicId: Int,
    val title: String,
    val video_id: String,
    val topic_label: String,
    val topic_label2: String,
    val content: String
)
