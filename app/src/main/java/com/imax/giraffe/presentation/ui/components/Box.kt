package com.imax.giraffe.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imax.giraffe.R
import com.imax.giraffe.presentation.data.db.entities.Grade

@Composable
fun Grade(
    grade: Grade,
    color: Color,
    picId: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        shape = RoundedCornerShape(
            28.dp
        ),
        onClick = {
            onClick()
        },
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.padding(start = 12.dp, top = 12.dp, bottom = 10.dp).size(80.dp),
                painter = painterResource(picId), // Замените на своё изображение
                contentDescription = "Lion Icon",
                contentScale = ContentScale.Fit // Подгоняет изображение
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp),
                text = grade.gradeName,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }
}

@Composable
@Preview
fun GradePreview() {
    Grade(grade = Grade(1, "Grade 1", "", "", "", ""), Color(0xFF74B731), R.drawable.grade_lion) {}
}