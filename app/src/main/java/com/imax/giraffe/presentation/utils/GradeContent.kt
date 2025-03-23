package com.imax.giraffe.presentation.utils

import androidx.compose.ui.graphics.Color
import com.imax.giraffe.R

enum class GradeContent(val color: Color, val picId: Int) {
    GRADE1(Color(0xFF74B731), R.drawable.grade_rabbit),
    GRADE2(Color(0xFF917BFF), R.drawable.grade_fox),
    GRADE3(Color(0xFF59A6F2), R.drawable.grade_tiger),
    GRADE4(
        Color(
            0xFFFF773E
        ),
        R.drawable.grade_lion
    ),
}