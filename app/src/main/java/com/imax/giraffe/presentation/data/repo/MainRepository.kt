package com.imax.giraffe.presentation.data.repo

import com.imax.giraffe.presentation.data.db.GiraffeDao
import com.imax.giraffe.presentation.data.db.entities.GradeTopic
import com.imax.giraffe.presentation.data.db.entities.Levels
import com.imax.giraffe.presentation.utils.parseLevelsJson
import com.imax.giraffe.presentation.utils.parseTopicsJson
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dao: GiraffeDao
) {

    suspend fun getGrades() = dao.getGrades()

    suspend fun getGrade(gradeId: Int) = dao.getGrade(gradeId)

    suspend fun getGradeTopics(gradeId: Int): GradeTopic? {
        val jsonString = dao.getGrade(gradeId).topic
        return parseTopicsJson(jsonString)
    }

    suspend fun getGradeLevels(gradeId: Int): Levels {
        val jsonString = dao.getGrade(gradeId).levels
        return parseLevelsJson(jsonString)
    }

    suspend fun getListeningTests(gradeId: Int, levelId: Int) =
        dao.getListeningTests(gradeId, levelId)

    suspend fun getWritingTests(gradeId: Int, topicId: Int) = dao.getWritingTests(gradeId, topicId)

    suspend fun getReadingTests(gradeId: Int, levelId: Int) = dao.getReadingTests(gradeId, levelId)

    suspend fun getVocabulary(gradeId: Int, levelId: Int) = dao.getVocabulary(gradeId, levelId)

}