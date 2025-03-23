package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.ui.theme.blockedTopic
import com.imax.giraffe.presentation.ui.theme.disabledButton
import com.imax.giraffe.presentation.ui.theme.gray
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.greenTypography
import com.imax.giraffe.presentation.ui.theme.primaryColor
import com.imax.giraffe.presentation.utils.getDrawableResourceId

@Composable
fun TopicScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit
) {

    LaunchedEffect(viewModel) {
        viewModel.getGradeTopics(1)
    }
    val topics = viewModel.getGradeTopicResult.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.background2),
                contentScale = ContentScale.Crop
            )
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Hi Atabek!",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    text = stringResource(R.string.welcome_to_school_education),
                    modifier = Modifier.padding(top = 6.dp),
                    style = TextStyle(
                        color = grayTypography,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Image(
                painter = painterResource(R.drawable.pic_giraffe),
                modifier = Modifier.size(54.dp),
                contentDescription = "Giraffe"
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.pic_grade_lion_bg2),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .matchParentSize() // Растянет картинку на всю область Box
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
                        text = "Lion. 4th Grade",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Text(
                        text = "Level 1",
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
                        val feedCount = viewModel.getFeedCount()
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
        val firstTopicCompletedPercent =
            if (viewModel.isFirstTopicCompleted()) 100 else viewModel.getTopicCompletedPercent()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
        ) {
            val resId = getDrawableResourceId(topics?.topic1?.pic ?: "pic_grade1_topic1")
            Image(
                painter = painterResource(resId),
                contentDescription = null,
                contentScale = ContentScale.FillBounds, // Оставляем пропорции
                modifier = Modifier
                    .align(Alignment.CenterEnd) // Выравниваем картинку вправо
                    .size(200.dp) // Устанавливаем фиксированный размер, если нужно
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 100.dp), // Уменьшаем ширину, чтобы оставить место под картинку
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = topics?.topic2?.name.toString(),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Text(
                        text = "Theme 1",
                        style = TextStyle(
                            color = gray,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        ),
                    )

                    Text(
                        modifier = Modifier.padding(top = 60.dp),
                        text = "$firstTopicCompletedPercent%",
                        style = TextStyle(
                            color = greenTypography,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
        val cardColor =
            if (viewModel.isFirstTopicCompleted()) Color.White else blockedTopic.copy(alpha = 0.5f)
        val secondTopicCompletedPercent =
            if (viewModel.isFirstTopicCompleted()) viewModel.getTopicCompletedPercent() else 0
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(cardColor)
        ) {

            val resId = getDrawableResourceId(topics?.topic2?.pic ?: "pic_grade1_topic1")
            Image(
                painter = painterResource(resId),
                contentDescription = null,
                contentScale = ContentScale.FillBounds, // Оставляем пропорции
                modifier = Modifier
                    .align(Alignment.CenterEnd) // Выравниваем картинку вправо
                    .size(200.dp) // Устанавливаем фиксированный размер, если нужно
            )

            if (!viewModel.isFirstTopicCompleted())
                Image(
                    painter = painterResource(R.drawable.ic_lock),
                    contentDescription = "lock",
                    modifier = Modifier.align(Alignment.Center)
                )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 100.dp), // Уменьшаем ширину, чтобы оставить место под картинку
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = topics?.topic2?.name.toString(),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Text(
                        text = "Theme 2",
                        style = TextStyle(
                            color = gray,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        ),
                    )

                    Text(
                        modifier = Modifier.padding(top = 60.dp),
                        text = "$secondTopicCompletedPercent%",
                        style = TextStyle(
                            color = greenTypography,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }


    }

}

@Composable
@Preview()
fun TopicPreview() {
    TopicScreen(onNavigateToScreen = {})
}