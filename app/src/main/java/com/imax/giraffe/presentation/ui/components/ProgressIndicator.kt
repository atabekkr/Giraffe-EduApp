package com.imax.giraffe.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imax.giraffe.presentation.ui.theme.primaryColor

@Composable
fun TestProgress(
    currentQuestion: Int,
    totalQuestions: Int,
    modifier: Modifier = Modifier
) {
    val progress = remember(currentQuestion, totalQuestions) {
        (currentQuestion - 1).toFloat() / totalQuestions.toFloat()
    }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(8.dp))
                .height(8.dp),
            color = primaryColor,
            trackColor = Color.White
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Текст справа
        Text(
            text = "$currentQuestion / $totalQuestions",
            style = TextStyle(
                color = primaryColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}
