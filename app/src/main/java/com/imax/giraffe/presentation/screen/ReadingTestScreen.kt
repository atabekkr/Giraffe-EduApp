package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.screen.dialog.CongratsDialog
import com.imax.giraffe.presentation.screen.dialog.CorrectDialog
import com.imax.giraffe.presentation.screen.dialog.ErrorDialog
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.ui.components.ReadingSentenceCard
import com.imax.giraffe.presentation.ui.components.StandardButtonWithoutPadding
import com.imax.giraffe.presentation.ui.components.TestProgress
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.primaryColor
import com.imax.giraffe.presentation.utils.parseReadingAnswersJson

@Composable
fun ReadingTestScreen(
    viewModel: MainViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit
) {

    val gradeId = userViewModel.getGradeId()
    val topicId = userViewModel.getTopicId()
    LaunchedEffect(viewModel) {
        viewModel.getReadingTests(gradeId, topicId)
    }
    val tests = viewModel.getReadingTestsResult.collectAsState().value

    var index by remember { mutableIntStateOf(0) }
    val readingTest = tests?.getOrNull(index)

    val answers = parseReadingAnswersJson(readingTest?.answers ?: "")

    var selectedOption by remember { mutableStateOf<String?>(null) }

    var showWrongDialog by remember { mutableStateOf(false) }
    var showCorrectDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.background2),
                contentScale = ContentScale.Crop
            )
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (showWrongDialog) {
            ErrorDialog { showWrongDialog = false }
        }
        if (showCorrectDialog) {
            selectedOption = ""
            if (tests?.getOrNull(index + 1) != null)
                CorrectDialog {
                    showCorrectDialog = false
                    index++
                }
            else
                CongratsDialog {
                    userViewModel.setReadingTestCompleted()
                    userViewModel.incrementLevelIndex()
                    onNavigateToScreen(Screen.Feed)
                }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Reading! \uD83D\uDCD9 ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Boost your listening with Saribek.",
                    fontSize = 14.sp,
                    color = grayTypography
                )
            }
            Image(
                painter = painterResource(id = R.drawable.pic_giraffe),
                contentDescription = "Avatar",
                modifier = Modifier.size(50.dp)
            )
        }

        TestProgress(
            modifier = Modifier.padding(top = 50.dp, start = 12.dp, end = 12.dp),
            currentQuestion = index + 1,
            totalQuestions = tests?.size ?: 0
        )

        ReadingSentenceCard(
            modifier = Modifier.padding(top = 26.dp),
            firstPart = readingTest?.firstPart,
            secondPart = readingTest?.secondPart
        )

        SelectableButtons(
            options = answers,
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it })

        Spacer(modifier = Modifier.weight(1f))

        StandardButtonWithoutPadding(
            modifier = Modifier.padding(bottom = 48.dp),
            text = "Check"
        ) {
            if (selectedOption == readingTest?.key) showCorrectDialog = true
            else showWrongDialog = true
        }
    }
}

@Composable
fun SelectableButtons(
    options: List<String>, selectedOption: String?,
    onOptionSelected: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        options.chunked(2).forEach { rowOptions ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                rowOptions.forEach { option ->
                    Button(
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .border(
                                width = if (selectedOption == option) 2.dp else 0.dp,
                                color = if (selectedOption == option) primaryColor else Color.Transparent,
                                shape = RoundedCornerShape(16.dp)
                            ),
                        onClick = {
                            onOptionSelected(option)
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text(
                            text = option,
                            color = primaryColor,
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
                        )
                    }
                }
            }
        }
    }
}
