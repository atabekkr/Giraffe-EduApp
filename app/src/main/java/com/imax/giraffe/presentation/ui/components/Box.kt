package com.imax.giraffe.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Grade(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(276.dp)
            .padding(bottom = 16.dp)
            .clickable { onClick.invoke() }
    ) {
        Card(
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .width(300.dp)
                .height(240.dp)
                .padding(top = 28.dp),
            shape = RoundedCornerShape(
                30.dp
            ),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFF773E))
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    text = "Grade 4",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    text = "The lion symbolizes leadership and wisdom. Fourth graders become mature leaders, for pupils. ",
                    style = TextStyle(color = Color.White, fontSize = 18.sp)
                )
            }
        }
        LionIcon(
            modifier = Modifier
                .align(alignment = Alignment.BottomStart)
                .padding(8.dp)
                .size(120.dp)
        )
    }
}