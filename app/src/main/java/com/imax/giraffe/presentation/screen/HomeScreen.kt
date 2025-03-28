package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.Image
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
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.ui.components.GradeCard
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
    onNavigateToScreen: (Screen) -> Unit
) {

    val gradeId = userViewModel.getGradeId()

    LaunchedEffect(mainViewModel) {
//        mainViewModel.getGradeTopics(gradeId)
//        viewModel.getGradeLevels(gradeId)
        mainViewModel.getGrade(gradeId)
    }

    val grade = mainViewModel.getGradeResult.collectAsState().value
    val testSectionPic = grade?.test?.let { parseTestSectionCardPicJson(it) }
    val userName = userViewModel.getUserName()

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
                painter = painterResource(R.drawable.pic_giraffe),
                modifier = Modifier.size(54.dp),
                contentDescription = "Giraffe"
            )
        }
        GradeCard(
            grade = grade,
            level = "Level ${userViewModel.getLevelIndex() + 1}",
            gradeContent = gradeContent,
            feedCount = mainViewModel.getFeedCount()
        ) { }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(
                24.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    onNavigateToScreen.invoke(
                        Screen.ListeningTest
                    )
                }
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    val resId = getDrawableResourceId(testSectionPic?.picListening ?: "pic_lion_1")
                    Image(
                        painter = painterResource(resId),
                        modifier = Modifier
                            .size(135.dp)
                            .padding(horizontal = 12.dp),
                        contentDescription = "Lion1"
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 2.dp)
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
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    onNavigateToScreen.invoke(
                        Screen.ReadingTest
                    )
                }
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    val resId = getDrawableResourceId(testSectionPic?.picReading ?: "pic_lion_1")
                    Image(
                        painter = painterResource(resId),
                        modifier = Modifier
                            .size(135.dp)
                            .padding(horizontal = 12.dp),
                        contentDescription = "Lion1"
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 2.dp)
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
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    onNavigateToScreen.invoke(
                        Screen.WritingTest
                    )
                }
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    val resId = getDrawableResourceId(testSectionPic?.picWriting ?: "pic_lion_1")
                    Image(
                        painter = painterResource(resId),
                        modifier = Modifier
                            .size(135.dp)
                            .padding(horizontal = 12.dp),
                        contentDescription = "Lion1"
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 2.dp)
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
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    onNavigateToScreen.invoke(
                        Screen.SpeakingTest
                    )
                }
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.pic_speaking_lion_card),
                        modifier = Modifier
                            .size(135.dp)
                            .padding(horizontal = 12.dp),
                        contentDescription = "Lion1"
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 2.dp)
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