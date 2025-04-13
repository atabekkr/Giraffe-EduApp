package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.imax.giraffe.presentation.screen.dialog.StartMatchingDialog
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.ui.components.GradeCard
import com.imax.giraffe.presentation.ui.theme.blockedTopic
import com.imax.giraffe.presentation.ui.theme.gray
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.greenTypography
import com.imax.giraffe.presentation.ui.theme.mainTypography
import com.imax.giraffe.presentation.utils.GradeContent
import com.imax.giraffe.presentation.utils.getDrawableResourceId

@Composable
fun TopicScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit
) {

    val context = LocalContext.current

    val gradeId = userViewModel.getGradeId()

    LaunchedEffect(viewModel) {
        viewModel.getGradeTopics(gradeId)
        viewModel.getGradeLevels(gradeId)
        viewModel.getGrade(gradeId)
    }

    val topics = viewModel.getGradeTopicResult.collectAsState().value
    val level = "Level ${userViewModel.getLevelIndex() + 1}"
    val grade = viewModel.getGradeResult.collectAsState().value
    val levelIndex = userViewModel.getLevelIndex()

    val username = userViewModel.getUserName()
    val gradeContent = when (userViewModel.getGradeId()) {
        1 -> GradeContent.GRADE1
        2 -> GradeContent.GRADE2
        3 -> GradeContent.GRADE3
        4 -> GradeContent.GRADE4
        else -> GradeContent.GRADE1
    }

    var showStartMatchingDialog by remember { mutableStateOf(false) }

    if (topics == null) {
        CircularProgressIndicator(modifier = Modifier.padding(top = 100.dp))
    } else {
        // основной UI
        Column(
            modifier = modifier
                .fillMaxSize()
                .paint(
                    painterResource(R.drawable.background2),
                    contentScale = ContentScale.Crop
                )
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            if (showStartMatchingDialog) {
                StartMatchingDialog {
                    showStartMatchingDialog = false
                    onNavigateToScreen.invoke(Screen.Matching)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Hi $username!",
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
                            fontSize = 16.sp,
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
                level = level,
                gradeContent = gradeContent,
                feedCount = userViewModel.getFeedCount()
            ) {
                onNavigateToScreen(Screen.Feed)
            }

            val firstTopicCompletedPercent = userViewModel.getTopicCompletedPercent()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .clickable {
                        if (userViewModel.isFirstTopicCompleted()) return@clickable
                        if (levelIndex != 0)
                            onNavigateToScreen.invoke(Screen.Home)
                        else
                            showStartMatchingDialog = true
                    }
            ) {
                val resId = getDrawableResourceId(context, topics?.topic1?.pic)
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
                            text = topics?.topic1?.name.toString(),
                            style = TextStyle(
                                color = mainTypography,
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
                if (userViewModel.isFirstTopicCompleted()) Color.White else blockedTopic.copy(alpha = 0.5f)
            val secondTopicCompletedPercent =
                if (userViewModel.isFirstTopicCompleted()) userViewModel.getTopicCompletedPercent() else 0
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 48.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(cardColor)
                    .clickable(enabled = userViewModel.isFirstTopicCompleted()) {
                        onNavigateToScreen.invoke(Screen.Home)
                    }
            ) {

                val resId =
                    getDrawableResourceId(context, topics.topic2.pic)
                Image(
                    painter = painterResource(resId),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds, // Оставляем пропорции
                    modifier = Modifier
                        .align(Alignment.CenterEnd) // Выравниваем картинку вправо
                        .size(200.dp) // Устанавливаем фиксированный размер, если нужно
                )

                if (!userViewModel.isFirstTopicCompleted())
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
                                color = mainTypography,
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

}

@Composable
@Preview()
fun TopicPreview() {
    TopicScreen(onNavigateToScreen = {})
}