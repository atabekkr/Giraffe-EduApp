package com.imax.giraffe.presentation.data.db

import androidx.room.Dao
import androidx.room.Query
import com.imax.giraffe.presentation.data.db.entities.Grade

@Dao
interface GiraffeDao {
    @Query("SELECT * FROM Grades")
    suspend fun getGrades(): List<Grade>
}