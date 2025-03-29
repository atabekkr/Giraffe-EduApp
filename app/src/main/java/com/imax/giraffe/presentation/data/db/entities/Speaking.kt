package com.imax.giraffe.presentation.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Speaking")
data class Speaking(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "grade_id")
    val gradeId: Int,
    @ColumnInfo(name = "topic_id")
    val topicId: Int,
    val audio: String,
    @ColumnInfo("audio_text")
    val text: String
)
