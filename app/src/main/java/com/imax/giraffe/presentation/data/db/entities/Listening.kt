package com.imax.giraffe.presentation.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Listening")
data class Listening(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("grade_id")
    val gradeId: Int,
    @ColumnInfo(name = "topic_id")
    val topicId: Int,
    val audio: String,
    val text: String
)
