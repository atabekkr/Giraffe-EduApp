package com.imax.giraffe.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import com.imax.giraffe.presentation.data.db.entities.GradeTopic
import com.imax.giraffe.presentation.data.db.entities.Levels

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

@Composable
fun getDrawableResourceId(resourceName: String?): Int {
    val resource = resourceName ?: "lion_pic"
    val context = LocalContext.current
    return remember(resourceName) {
        context.resources.getIdentifier(resource, "drawable", context.packageName)
    }
}

