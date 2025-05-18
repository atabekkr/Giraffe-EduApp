package com.imax.giraffe.presentation.data.repo

import com.imax.giraffe.presentation.data.db.GradeCompletionDao
import com.imax.giraffe.presentation.data.db.entities.GradeCompletionData
import com.imax.giraffe.presentation.utils.LocalStorage
import javax.inject.Inject

class GradeCompletionRepository @Inject constructor(
    private val dao: GradeCompletionDao,
    private val localStorage: LocalStorage
) {

    suspend fun getGradeData(): GradeCompletionData {
        val isListeningTestCompleted =
            dao.getGradeData(localStorage.gradeId).isListeningTestCompleted
        val isSpeakingTestCompleted = dao.getGradeData(localStorage.gradeId).isSpeakingTestCompleted
        val isWritingTestCompleted = dao.getGradeData(localStorage.gradeId).isWritingTestCompleted
        val isReadingTestCompleted = dao.getGradeData(localStorage.gradeId).isReadingTestCompleted
        if (isListeningTestCompleted && isSpeakingTestCompleted && isWritingTestCompleted && isReadingTestCompleted) {
            resetTestCompletionResult()
        }
        return dao.getGradeData(localStorage.gradeId)
    }

    suspend fun incrementFeedCount() = dao.incrementFeedCount(localStorage.gradeId)
    suspend fun resetFeedCount() = dao.resetFeedCount(localStorage.gradeId)
    suspend fun incrementLevel(levelCount: Int) {
        dao.incrementLevel(localStorage.gradeId, levelCount)
    }

    suspend fun incrementTopicCompletedPercent() {
        if (localStorage.topicId == 1)
            dao.incrementFirstTopicCompletedPercent(localStorage.gradeId)
        else
            dao.incrementSecondTopicCompletedPercent(localStorage.gradeId)
    }

    suspend fun updateListeningTestCompleted() =
        dao.updateListeningTestCompleted(localStorage.gradeId)

    suspend fun updateSpeakingTestCompleted() =
        dao.updateSpeakingTestCompleted(localStorage.gradeId)

    suspend fun updateWritingTestCompleted() = dao.updateWritingTestCompleted(localStorage.gradeId)
    suspend fun updateReadingTestCompleted() = dao.updateReadingTestCompleted(localStorage.gradeId)

    suspend fun resetTestCompletionResult() = dao.resetTestCompletionResult(localStorage.gradeId)

}