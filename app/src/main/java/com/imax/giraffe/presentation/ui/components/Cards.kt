package com.imax.giraffe.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imax.giraffe.presentation.ui.theme.primaryColor

@Composable
fun SoundCard(
    modifier: Modifier = Modifier,
    iconRes: Int,
    size: Dp,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .size(size),
        onClick = { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = "Sound Icon",
                tint = primaryColor,
                modifier = Modifier.size(64.dp)
            )
        }
    }
}

@Composable
fun SentenceCard(
    selectedWords: List<String>,
    onClear: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(342.dp)
            .height(142.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp, end = 15.dp),
            contentAlignment = Alignment.TopEnd // Размещаем кнопку в верхнем правом углу
        ) {
            OutlinedButton(
                onClick = { onClear() },
                modifier = Modifier.size(24.dp),
                shape = CircleShape,
                border = BorderStroke(width = 0.dp, primaryColor),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = primaryColor)
            ) {
                Icon(
                    Icons.Default.Close,
                    modifier = Modifier.padding(4.dp),
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            // Текст, формируемый из выбранных слов
            Text(
                text = selectedWords.joinToString(" "),
                fontSize = 20.sp,
                lineHeight = 36.sp,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Разделители
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(32.dp))
                Divider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(36.dp))
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}