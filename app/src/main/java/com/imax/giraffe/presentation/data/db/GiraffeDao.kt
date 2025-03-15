package com.imax.giraffe.presentation.data.db

import androidx.room.Dao
import androidx.room.Query
import com.imax.giraffe.presentation.data.db.entities.Grade
import com.imax.giraffe.presentation.data.db.entities.Listening

@Dao
interface GiraffeDao {
    @Query("SELECT * FROM Grades")
    suspend fun getGrades(): List<Grade>

    @Query("SELECT * FROM Listening WHERE grade_id = :gradeId AND level_id = :levelId")
    suspend fun getListeningTests(gradeId: Int, levelId: Int): List<Listening>

}