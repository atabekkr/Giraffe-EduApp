package com.imax.giraffe.presentation.data.repo

import com.imax.giraffe.presentation.data.db.GiraffeDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dao: GiraffeDao
) {

    suspend fun getGrades() = dao.getGrades()

    fun getGrade(gradeId: Int) = dao.getGrade(gradeId)

    suspend fun getListeningTests(gradeId: Int, levelId: Int) = dao.getListeningTests(gradeId, levelId)

    suspend fun getWritingTests(gradeId: Int, levelId: Int) = dao.getWritingTests(gradeId, levelId)

    suspend fun getReadingTests(gradeId: Int, levelId: Int) = dao.getReadingTests(gradeId, levelId)

}