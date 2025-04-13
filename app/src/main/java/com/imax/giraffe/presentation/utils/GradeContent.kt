package com.imax.giraffe.presentation.utils

import androidx.compose.ui.graphics.Color
import com.imax.giraffe.R

enum class GradeContent(val color: Color, val picId: Int, val gradeName: String) {
    GRADE1(Color(0xFF74B731), R.drawable.grade_rabbit, "Rabbit.1st Grade"),
    GRADE2(Color(0xFF917BFF), R.drawable.grade_fox, "Fox.2nd Grade"),
    GRADE3(Color(0xFF59A6F2), R.drawable.grade_tiger, "Tiger.3rd Grade"),
    GRADE4(
        Color(
            0xFFFF773E
        ),
        R.drawable.grade_lion,
        "Lion.4th Grade"
    ),
}

enum class Grade(val id: Int) {
    GRADE1(1),
    GRADE2(2),
    GRADE3(3),
    GRADE4(4)
}