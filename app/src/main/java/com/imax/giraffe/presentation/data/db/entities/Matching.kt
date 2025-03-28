package com.imax.giraffe.presentation.data.db.entities

import android.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.imax.giraffe.presentation.ui.theme.primaryColor

data class Matching(
    val id: Int
)

data class MatchingUI(
    val word: String,
    val borderColor: BorderColor = BorderColor.TRANSPARENT,
    val isCompleted: Boolean = false
)

enum class BorderColor(val color: Int) {
    TRANSPARENT(Color.TRANSPARENT),
    SELECTED(primaryColor.toArgb()),
    INCORRECT(Color.RED),
    COMPLETED(Color.GREEN),

}
