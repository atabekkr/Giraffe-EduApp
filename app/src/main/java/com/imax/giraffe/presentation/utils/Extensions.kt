package com.imax.giraffe.presentation.utils

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import com.imax.giraffe.presentation.data.db.entities.GradeTopic
import com.imax.giraffe.presentation.data.db.entities.Levels
import com.imax.giraffe.presentation.data.db.entities.ReadingAnswers
import com.imax.giraffe.presentation.data.db.entities.TestSectionPic
import com.imax.giraffe.presentation.data.db.entities.Words

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

@Composable
fun getDrawableResourceId(resourceName: String?): Int {
    val resource = resourceName ?: "lion_pic"
    val context = LocalContext.current
    return remember(resourceName) {
        context.resources.getIdentifier(resource, "drawable", context.packageName)
    }
}

fun getRawResourceId(context: Context, resourceName: String?): Int {
    Log.d("getRawResourceId", "getRawResourceId: $resourceName")
    val resource = resourceName ?: "test" // Аудио по умолчанию
    return context.resources.getIdentifier(resource, "raw", context.packageName)
}

