package com.imax.giraffe.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import com.imax.giraffe.presentation.data.db.entities.GradeTopic

fun parseTopicsJson(jsonString: String): GradeTopic {
    return Gson().fromJson(jsonString, GradeTopic::class.java)
}

@Composable
fun getDrawableResourceId(resourceName: String): Int {
    val context = LocalContext.current
    return remember(resourceName) {
        context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    }
}

