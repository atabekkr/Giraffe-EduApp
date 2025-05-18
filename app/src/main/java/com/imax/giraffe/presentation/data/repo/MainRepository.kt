package com.imax.giraffe.presentation.data.repo

import com.imax.giraffe.presentation.data.db.GiraffeDao
import com.imax.giraffe.presentation.data.db.entities.GradeTopic
import com.imax.giraffe.presentation.data.db.entities.Levels
import com.imax.giraffe.presentation.utils.LocalStorage
import com.imax.giraffe.presentation.utils.parseLevelsJson
import com.imax.giraffe.presentation.utils.parseTopicsJson
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dao: GiraffeDao,
    private val localStorage: LocalStorage
) {

    suspend fun getGrades() = dao.getGrades()

    suspend fun getGrade() = dao.getGrade(localStorage.gradeId)

    suspend fun getGradeTopics(): GradeTopic? {
        val jsonString = dao.getGrade(localStorage.gradeId).topic
        return parseTopicsJson(jsonString)
    }

    suspend fun getGradeLevels(): Levels {
        val jsonString = dao.getGrade(localStorage.gradeId).levels
        return parseLevelsJson(jsonString)
    }

    suspend fun getListeningTests() =
        dao.getListeningTests(gradeId = localStorage.gradeId, topicId = localStorage.topicId)

    suspend fun getSpeakingTests() =
        dao.getSpeakingTests(gradeId = localStorage.gradeId, topicId = localStorage.topicId)

    suspend fun getWritingTests() = dao.getWritingTests(gradeId = localStorage.gradeId, topicId = localStorage.topicId)

    suspend fun getReadingTests() = dao.getReadingTests(gradeId = localStorage.gradeId, topicId = localStorage.topicId)

    suspend fun getVocabulary() = dao.getVocabulary(gradeId = localStorage.gradeId, topicId = localStorage.topicId)

    suspend fun getTopicContent() = dao.getTopicContent(gradeId = localStorage.gradeId, topicId = localStorage.topicId)

}