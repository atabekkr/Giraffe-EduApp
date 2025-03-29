package com.imax.giraffe.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
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
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val iconSize = if (size == 72.dp) 36.dp else 64.dp

    Card(
        modifier = modifier
            .size(size)
            .border(
                width = if (isSelected) 4.dp else 0.dp, // Добавляем бордер при выборе
                color = if (isSelected) primaryColor else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            ),
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
                modifier = Modifier.size(iconSize)
            )
        }
    }
}

@Composable
fun SentenceCard(
    modifier: Modifier = Modifier,
    selectedWords: List<String>,
    onClear: () -> Unit
) {
    Card(
        modifier = modifier
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

@Composable
fun ReadingSentenceCard(
    modifier: Modifier = Modifier,
    firstPart: String?,
    secondPart: String?
) {
    Card(
        modifier = modifier
            .width(342.dp)
            .height(142.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 30.dp)
        ) {
            ReadingText(firstPart, secondPart)
        }
    }
}

@Composable
fun ReadingText(firstPart: String?, secondPart: String?) {
    val underlineWidth = 50.sp // Можно менять ширину подчёркивания

    val annotatedString = buildAnnotatedString {
        append(firstPart ?: "")
        appendInlineContent("gap", " ")
        append(secondPart ?: "")
    }

    val inlineContent = mapOf(
        "gap" to InlineTextContent(
            Placeholder(underlineWidth, 2.sp, PlaceholderVerticalAlign.TextBottom) // Ставим линию между текстом
        ) {
            Canvas(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .width(50.dp)
                    .height(2.dp)
            ) {
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = size.height
                )
            }
        }
    )

    Text(
        text = annotatedString,
        inlineContent = inlineContent,
        fontSize = 20.sp,
        lineHeight = 36.sp,
        maxLines = 2,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}

