package com.imax.giraffe.presentation.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Writing")
data class Writing(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "grade_id")
    val gradeId: Int,
    @ColumnInfo(name = "level_id")
    val levelId: Int,
    val audio: String,
    val text: String
)
