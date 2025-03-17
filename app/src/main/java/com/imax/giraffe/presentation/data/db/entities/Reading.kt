package com.imax.giraffe.presentation.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Reading")
data class Reading(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "grade_id")
    val gradeId: Int,
    @ColumnInfo(name = "topic_id")
    val topicId: Int,
    @ColumnInfo(name = "first_part")
    val firstPart: String,
    @ColumnInfo(name = "second_part")
    val secondPart: String,
    val answers: String,
    val key: String
)
