package com.imax.giraffe.presentation.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

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

data class Levels(
    val data: List<Level>
)

data class Level(
    val name: String,
    val pic: String
)

data class TestSectionPic(
    @SerializedName("pic_listening")
    val picListening: String?,
    @SerializedName("pic_speaking")
    val picSpeaking: String?,
    @SerializedName("pic_reading")
    val picReading: String?,
    @SerializedName("pic_writing")
    val picWriting: String?
)