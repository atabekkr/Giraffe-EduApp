package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.screen.viewmodel.GradeDataViewModel
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.ui.components.GradeCard
import com.imax.giraffe.presentation.ui.theme.completedColor
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.mainTypography
import com.imax.giraffe.presentation.utils.GradeContent
import com.imax.giraffe.presentation.utils.getDrawableResourceId
import com.imax.giraffe.presentation.utils.parseTestSectionCardPicJson

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    gradeDataViewModel: GradeDataViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(mainViewModel) {
        mainViewModel.getGrade()
    }

    gradeDataViewModel.getGrade()

    val grade = mainViewModel.getGradeResult.collectAsState().value
    val testSectionPic = grade?.test?.let { parseTestSectionCardPicJson(it) }
    val userName = userViewModel.getUserName()

    val gradeCompletionData = gradeDataViewModel.getGradeDataResult.collectAsState().value

    val gradeContent = when (userViewModel.getGradeId()) {
        1 -> GradeContent.GRADE1
        2 -> GradeContent.GRADE2
        3 -> GradeContent.GRADE3
        4 -> GradeContent.GRADE4
        else -> GradeContent.GRADE1
    }

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
                    text = "Hi $userName!",
                    style = TextStyle(
                        color = mainTypography,
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
                painter = painterResource(R.drawable.ic_topic_overview),
                modifier = Modifier.clickable { onNavigateToScreen.invoke(Screen.TopicOverview) },
                contentDescription = "Giraffe"
            )
        }
        GradeCard(
            grade = grade,
            level = "Level ${gradeCompletionData?.level}",
            gradeContent = gradeContent,
            feedCount = gradeCompletionData?.feed_count ?: 0
        ) {
            onNavigateToScreen.invoke(
                Screen.Feed
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(
                24.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            val containerColor =
                if (gradeCompletionData?.isListeningTestCompleted == true) completedColor else Color.White
            val listeningCardEnabled = gradeCompletionData?.isListeningTestCompleted ?: false
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = containerColor,
                    disabledContainerColor = completedColor
                ),
                shape = RoundedCornerShape(20.dp),
                enabled = !listeningCardEnabled,
                onClick = {
                    onNavigateToScreen.invoke(
                        Screen.ListeningTest
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val resId = getDrawableResourceId(
                        context,
                        testSectionPic?.picListening ?: "pic_listening_lion_card"
                    )
                    Image(
                        painter = painterResource(resId),
                        modifier = Modifier
                            .size(135.dp)
                            .padding(horizontal = 12.dp),
                        contentDescription = "Lion1"
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = "Listening",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    )
                }
            }
            val readingCardContainerColor =
                if (gradeCompletionData?.isReadingTestCompleted == true) completedColor else Color.White
            val readingCardEnabled = gradeCompletionData?.isReadingTestCompleted ?: false
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = readingCardContainerColor,
                    disabledContainerColor = completedColor
                ),
                shape = RoundedCornerShape(20.dp),
                enabled = !readingCardEnabled,
                onClick = {
                    onNavigateToScreen.invoke(
                        Screen.ReadingTest
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val resId = getDrawableResourceId(context, testSectionPic?.picReading)
                    Image(
                        painter = painterResource(resId),
                        modifier = Modifier
                            .size(135.dp)
                            .padding(horizontal = 12.dp),
                        contentDescription = "Lion1"
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = "Reading",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(
                24.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            val writingCardContainerColor =
                if (gradeCompletionData?.isWritingTestCompleted == true) completedColor else Color.White
            val writingCardEnabled = gradeCompletionData?.isWritingTestCompleted ?: false
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = writingCardContainerColor,
                    disabledContainerColor = completedColor
                ),
                shape = RoundedCornerShape(20.dp),
                enabled = !writingCardEnabled,
                onClick = {
                    onNavigateToScreen.invoke(
                        Screen.WritingTest
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val resId = getDrawableResourceId(context, testSectionPic?.picWriting)
                    Image(
                        painter = painterResource(resId),
                        modifier = Modifier
                            .size(135.dp)
                            .padding(horizontal = 12.dp),
                        contentDescription = "Lion1"
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = "Writing",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    )
                }
            }
            val speakingCardContainerColor =
                if (gradeCompletionData?.isSpeakingTestCompleted == true) completedColor else Color.White
            val speakingCardEnabled = gradeCompletionData?.isSpeakingTestCompleted ?: false
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = speakingCardContainerColor,
                    disabledContainerColor = completedColor
                ),
                shape = RoundedCornerShape(20.dp),
                enabled = !speakingCardEnabled,
                onClick = {
                    onNavigateToScreen.invoke(
                        Screen.SpeakingTest
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val resId = getDrawableResourceId(context, testSectionPic?.picSpeaking)
                    Image(
                        painter = painterResource(resId),
                        modifier = Modifier
                            .size(135.dp)
                            .padding(horizontal = 12.dp),
                        contentDescription = "Lion1"
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = "Speaking",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }

}