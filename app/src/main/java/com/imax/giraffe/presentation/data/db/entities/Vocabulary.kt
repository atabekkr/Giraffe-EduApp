package com.imax.giraffe.presentation.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Vocabulary")
data class Vocabulary(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "grade_id")
    val gradeId: Int,
    @ColumnInfo(name = "topic_id")
    val topicId: Int,
    @ColumnInfo(name = "english_words")
    val englishWords: String,
    @ColumnInfo(name = "karakalpak_words")
    val karakalpakWords: String
)

data class Words(
    val data: List<String>
)
