package com.imax.giraffe.presentation.utils

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import com.google.gson.Gson
import com.imax.giraffe.R
import com.imax.giraffe.presentation.data.db.entities.GradeTopic
import com.imax.giraffe.presentation.data.db.entities.Levels
import com.imax.giraffe.presentation.data.db.entities.ReadingAnswers
import com.imax.giraffe.presentation.data.db.entities.TestSectionPic
import com.imax.giraffe.presentation.data.db.entities.Words
import com.imax.giraffe.presentation.models.ContentResponse

fun parseTopicsJson(jsonString: String): GradeTopic? {
    return try {
        Gson().fromJson(jsonString, GradeTopic::class.java)
    } catch (e: Exception) {
        null
    }
}

fun parseLevelsJson(jsonString: String): Levels {
    return try {
        Gson().fromJson(jsonString, Levels::class.java)
    } catch (e: Exception) {
        Levels(data = emptyList())
    }
}

fun parseVocabularyJson(jsonString: String): List<String> {
    return try {
        Gson().fromJson(jsonString, Words::class.java).data
    } catch (e: Exception) {
        emptyList()
    }
}

fun parseReadingAnswersJson(jsonString: String): List<String> {
    return try {
        Gson().fromJson(jsonString, ReadingAnswers::class.java).answers
    } catch (e: Exception) {
        emptyList()
    }
}

fun parseTestSectionCardPicJson(jsonString: String): TestSectionPic? {
    return try {
        Gson().fromJson(jsonString, TestSectionPic::class.java)
    } catch (e: Exception) {
        null
    }
}

fun getDrawableResourceId(context: Context, resourceName: String?): Int {
    val resource = resourceName ?: "pic_listening_lion_card"
    val resourceId = context.resources.getIdentifier(resource, "drawable", context.packageName)
    return if (resourceId !=0) resourceId else R.drawable.pic_listening_lion_card
}

fun getRawResourceId(context: Context, resourceName: String?): Int {
    Log.d("getRawResourceId", "getRawResourceId: $resourceName")
    val resource = resourceName ?: "test" // Аудио по умолчанию
    return context.resources.getIdentifier(resource, "raw", context.packageName)
}

fun isTextCorrect(recognizedText: String, correctAnswer: String): Boolean {
    val normalizedRecognized = recognizedText.trim().lowercase()
    val normalizedCorrect = correctAnswer.trim().lowercase()

    val distance = levenshtein(normalizedRecognized, normalizedCorrect)
    val maxLen = maxOf(normalizedRecognized.length, normalizedCorrect.length)

    val similarity = 1.0 - (distance.toDouble() / maxLen)
    Log.d("TTTT", similarity.toString())

    return similarity >= 0.88
}

fun isWritingTextCorrect(inputText: String, correctAnswer: String): Boolean {
    val normalizedRecognized = inputText.trim().lowercase()
    val normalizedCorrect = correctAnswer.trim().lowercase()

    val distance = levenshtein(normalizedRecognized, normalizedCorrect)
    val maxLen = maxOf(normalizedRecognized.length, normalizedCorrect.length)

    val similarity = 1.0 - (distance.toDouble() / maxLen)

    return similarity >= 0.95
}


fun levenshtein(a: String, b: String): Int {
    val dp = Array(a.length + 1) { IntArray(b.length + 1) }

    for (i in 0..a.length) dp[i][0] = i
    for (j in 0..b.length) dp[0][j] = j

    for (i in 1..a.length) {
        for (j in 1..b.length) {
            val cost = if (a[i - 1] == b[j - 1]) 0 else 1
            dp[i][j] = minOf(
                dp[i - 1][j] + 1,
                dp[i][j - 1] + 1,
                dp[i - 1][j - 1] + cost
            )
        }
    }

    return dp[a.length][b.length]
}

fun vibrate(context: Context, durationMillis: Long = 200) {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    val effect = VibrationEffect.createOneShot(durationMillis, VibrationEffect.DEFAULT_AMPLITUDE)
    vibrator.vibrate(effect)
}

fun Context.playCorrectAnswerSound() {
    val mediaPlayer = MediaPlayer.create(this, R.raw.correct_audio)
    mediaPlayer.setOnCompletionListener {
        it.release()
    }
    mediaPlayer.start()
}

fun Context.playCongratsSound() {
    val mediaPlayer = MediaPlayer.create(this, R.raw.finish_audio)
    mediaPlayer.setOnCompletionListener {
        it.release()
    }
    mediaPlayer.start()
}

fun String?.toContentResponseOrNull(): ContentResponse? {
    return if (this.isNullOrBlank()) {
        null
    } else {
        try {
            Gson().fromJson(this, ContentResponse::class.java)
        } catch (e: Exception) {
            null
        }
    }
}