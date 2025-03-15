package com.imax.giraffe.presentation.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Grades")
data class Grade(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "grade_name")
    val gradeName: String,
    @ColumnInfo(name = "grade_definition")
    val gradeDefinition: String,
    @ColumnInfo("grade_animal_pic")
    val gradeAnimalPic: String,
    @ColumnInfo("grade_color")
    val gradeColor: String?,
    val levels: String,
    val test: String
)
