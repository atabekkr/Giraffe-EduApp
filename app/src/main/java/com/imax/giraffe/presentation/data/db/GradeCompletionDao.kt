package com.imax.giraffe.presentation.data.db

import androidx.room.Dao
import androidx.room.Query
import com.imax.giraffe.presentation.data.db.entities.GradeCompletionData

@Dao
interface GradeCompletionDao {

    @Query("SELECT * FROM GradeCompletionData WHERE grade_id = :gradeId")
    suspend fun getGradeData(gradeId: Int): GradeCompletionData

    @Query("UPDATE GradeCompletionData SET feed_count = feed_count + 1 WHERE grade_id = :gradeId")
    suspend fun incrementFeedCount(gradeId: Int)

    @Query("UPDATE GradeCompletionData SET feed_count = 0 WHERE grade_id = :gradeId")
    suspend fun resetFeedCount(gradeId: Int)

    @Query("UPDATE GradeCompletionData SET level = level + :levelCount WHERE grade_id = :gradeId")
    suspend fun incrementLevel(gradeId: Int, levelCount: Int)

    @Query("UPDATE GradeCompletionData SET first_topic_completed_percent = first_topic_completed_percent + 20 WHERE grade_id = :gradeId")
    suspend fun incrementFirstTopicCompletedPercent(gradeId: Int)

    @Query("UPDATE GradeCompletionData SET second_topic_completed_percent = second_topic_completed_percent + 20 WHERE grade_id = :gradeId")
    suspend fun incrementSecondTopicCompletedPercent(gradeId: Int)

    @Query("UPDATE GradeCompletionData SET isListeningTestCompleted = 1 WHERE grade_id = :gradeId")
    suspend fun updateListeningTestCompleted(gradeId: Int)

    @Query("UPDATE GradeCompletionData SET isSpeakingTestCompleted = 1 WHERE grade_id = :gradeId")
    suspend fun updateSpeakingTestCompleted(gradeId: Int)

    @Query("UPDATE GradeCompletionData SET isWritingTestCompleted = 1 WHERE grade_id = :gradeId")
    suspend fun updateWritingTestCompleted(gradeId: Int)

    @Query("UPDATE GradeCompletionData SET isReadingTestCompleted = 1 WHERE grade_id = :gradeId")
    suspend fun updateReadingTestCompleted(gradeId: Int)

    @Query("UPDATE GradeCompletionData SET isListeningTestCompleted = 0, isSpeakingTestCompleted = 0, isWritingTestCompleted = 0, isReadingTestCompleted = 0 WHERE grade_id = :gradeId")
    suspend fun resetTestCompletionResult(gradeId: Int)

}