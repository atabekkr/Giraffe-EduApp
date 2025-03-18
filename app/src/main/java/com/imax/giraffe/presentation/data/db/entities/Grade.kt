package com.imax.giraffe.presentation.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Grades")
data class Grade(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "grade_name")
    val gradeName: String,
    @ColumnInfo("grade_animal_pic")
    val gradeAnimalPic: String,
    val levels: String,
    val test: String,
    val topic: String
)
data class Topic(
    val name: String,
    val pic: String
)

data class GradeTopic(
    val topic1: Topic,
    val topic2: Topic
)