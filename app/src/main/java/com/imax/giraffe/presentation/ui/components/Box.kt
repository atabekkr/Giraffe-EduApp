package com.imax.giraffe.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imax.giraffe.R
import com.imax.giraffe.presentation.data.db.entities.Grade
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.ui.theme.disabledButton
import com.imax.giraffe.presentation.ui.theme.gray
import com.imax.giraffe.presentation.ui.theme.lockedGradeButton
import com.imax.giraffe.presentation.ui.theme.primaryColor
import com.imax.giraffe.presentation.utils.GradeContent
import com.imax.giraffe.presentation.utils.getDrawableResourceId

@Composable
fun Grade(
    grade: Grade,
    color: Color,
    picId: Int,
    isLocked: Boolean,
    isCompleted: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            shape = RoundedCornerShape(
                28.dp
            ),
            enabled = !isLocked && !isCompleted,
            onClick = {
                onClick()
            },
            colors = CardDefaults.cardColors(containerColor = color, disabledContainerColor = lockedGradeButton)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp, bottom = 10.dp)
                        .size(80.dp),
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
        if (isLocked)
            Image(
                painter = painterResource(R.drawable.ic_lock),
                contentDescription = "lock",
                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 24.dp, top = 12.dp)
            )
        if (isCompleted)
            Image(
                painter = painterResource(R.drawable.ic_completed),
                contentDescription = "lock",
                modifier = Modifier.size(80.dp).align(Alignment.CenterEnd).padding(end = 24.dp, top = 12.dp)
            )
    }
}

@Composable
fun GradeCard(
    grade: Grade?,
    gradeContent: GradeContent,
    level: String,
    feedCount: Int,
    onNavigateToScreen: (Screen) -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        val resId = getDrawableResourceId(context, grade?.gradeAnimalPic)
        Image(
            painter = painterResource(resId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize()
        )

        Card(
            modifier = Modifier
                .fillMaxWidth(), // Уменьшает размер, чтобы фон был виден
            colors = CardDefaults.cardColors(containerColor = Color.Transparent), // Делаем фон карты прозрачным
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = gradeContent.gradeName,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    text = level,
                    style = TextStyle(
                        color = gray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    ),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 60.dp)
                ) {
                    val feedButtonColor = if (feedCount == 0) disabledButton else primaryColor
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(114.dp)
                            .height(38.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(feedButtonColor)
                            .clickable(enabled = feedCount != 0) {
                                onNavigateToScreen.invoke(Screen.Feed)
                            }
                    ) {
                        Text(
                            text = "Feed",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(38.dp)
                            .height(38.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFFFF1CF))
                            .clickable(enabled = false) {}
                            .border(
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(width = 2.dp, color = Color(0xFFFFBF08))
                            )
                    ) {
                        Text(
                            text = "$feedCount",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFBF08)
                        )
                    }
                }
            }
        }
    }
}