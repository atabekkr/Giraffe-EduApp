package com.imax.giraffe.presentation.data.db

import androidx.room.Dao
import androidx.room.Query
import com.imax.giraffe.presentation.data.db.entities.Grade
import com.imax.giraffe.presentation.data.db.entities.Listening
import com.imax.giraffe.presentation.data.db.entities.Reading
import com.imax.giraffe.presentation.data.db.entities.Speaking
import com.imax.giraffe.presentation.data.db.entities.Vocabulary
import com.imax.giraffe.presentation.data.db.entities.Writing

@Dao
interface GiraffeDao {
    @Query("SELECT * FROM Grades")
    suspend fun getGrades(): List<Grade>

    @Query("SELECT * FROM Grades WHERE id = :gradeId")
    suspend fun getGrade(gradeId: Int): Grade

    @Query("SELECT * FROM Listening WHERE grade_id = :gradeId AND topic_id = :topicId")
    suspend fun getListeningTests(gradeId: Int, topicId: Int): List<Listening>

    @Query("SELECT * FROM Speaking WHERE grade_id = :gradeId AND topic_id = :topicId")
    suspend fun getSpeakingTests(gradeId: Int, topicId: Int): List<Speaking>

    @Query("SELECT * FROM Writing WHERE grade_id = :gradeId AND topic_id = :topicId")
    suspend fun getWritingTests(gradeId: Int, topicId: Int): List<Writing>

    @Query("SELECT * FROM Reading WHERE grade_id = :gradeId AND topic_id = :topicId")
    suspend fun getReadingTests(gradeId: Int, topicId: Int): List<Reading>

    @Query("SELECT * FROM Vocabulary WHERE grade_id = :gradeId AND topic_id = :topicId")
    suspend fun getVocabulary(gradeId: Int, topicId: Int): Vocabulary

}