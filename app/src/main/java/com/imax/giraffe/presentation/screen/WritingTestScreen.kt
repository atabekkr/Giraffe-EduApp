package com.imax.giraffe.presentation.screen

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.imax.giraffe.presentation.ui.components.SoundCard
import com.imax.giraffe.presentation.ui.components.StandardButtonWithoutPadding
import com.imax.giraffe.presentation.ui.components.WritingTestInput
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.utils.getRawResourceId

@Composable
fun WritingTestScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit
) {

    val context = LocalContext.current

    val gradeId = userViewModel.getGradeId()
    val topicId = userViewModel.getTopicId()
    LaunchedEffect(viewModel) {
        viewModel.getWritingTests(gradeId, topicId)
    }
    val tests = viewModel.getWritingTestsResult.collectAsState().value

    var index by remember { mutableIntStateOf(0) }
    val writingTest = tests?.getOrNull(index)
    var inputText by remember { mutableStateOf("") }

    var showWrongDialog by remember { mutableStateOf(false) }
    var showCorrectDialog by remember { mutableStateOf(false) }

    var mediaPlayer: MediaPlayer? = null

    val snackbarHostState = remember { SnackbarHostState() }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Показываем Snackbar при изменении errorMessage
    LaunchedEffect(errorMessage) {
        errorMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            errorMessage = null // Сбрасываем ошибку после показа
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .paint(
                    painterResource(R.drawable.background2),
                    contentScale = ContentScale.Crop
                )
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showWrongDialog) {
                ErrorDialog { showWrongDialog = false }
            }
            if (showCorrectDialog) {
                mediaPlayer?.release()
                if (tests?.getOrNull(index + 1) != null)
                    CorrectDialog {
                        showCorrectDialog = false
                        inputText = ""
                        index++
                    }
                else
                    CongratsDialog {
                        userViewModel.setWritingTestCompleted()
                        userViewModel.incrementLevelIndex()
                        onNavigateToScreen(Screen.Home)
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
                        "Listening! 🎧",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        "Boost your listening with Saribek.",
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, top = 48.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SoundCard(iconRes = R.drawable.ic_sound, size = 132.dp) {
                    mediaPlayer?.release()
                    try {
                        mediaPlayer =
                            MediaPlayer.create(
                                context,
                                getRawResourceId(context, writingTest?.audio)
                            )
                        mediaPlayer?.playbackParams = mediaPlayer?.playbackParams!!.setSpeed(1f)
                        mediaPlayer?.seekTo(0)
                        mediaPlayer?.start()
                    } catch (e: Exception) {
                        errorMessage = "Audio file not found"
                    }
                }
                SoundCard(iconRes = R.drawable.ic_slow_sound, size = 96.dp) {
                    mediaPlayer?.release()
                    try {
                        mediaPlayer =
                            MediaPlayer.create(
                                context,
                                getRawResourceId(context, writingTest?.audio)
                            )
                        mediaPlayer!!.playbackParams = mediaPlayer!!.playbackParams.setSpeed(0.5f)
                        mediaPlayer!!.seekTo(0)
                        mediaPlayer!!.start()
                    } catch (e: Exception) {
                        errorMessage = "Audio file not found"
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            WritingTestInput(
                text = inputText,
                onTextChange = { inputText = it }
            )

            Spacer(modifier = Modifier.weight(1f))

            StandardButtonWithoutPadding(
                modifier = Modifier.padding(bottom = 48.dp),
                text = "Check"
            ) {
                Log.d("WritingTest", "WritingTestScreen: $inputText\n${writingTest?.text}")
                if (inputText == writingTest?.text) showCorrectDialog = true
                else showWrongDialog = true
            }
        }
    }
}