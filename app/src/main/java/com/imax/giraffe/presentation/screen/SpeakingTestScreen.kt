package com.imax.giraffe.presentation.screen

import android.media.MediaPlayer
import android.media.MediaRecorder
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.screen.dialog.CongratsDialog
import com.imax.giraffe.presentation.screen.dialog.CorrectDialog
import com.imax.giraffe.presentation.screen.dialog.ErrorDialog
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.screen.viewmodel.VoskViewModel
import com.imax.giraffe.presentation.ui.components.SoundCard
import com.imax.giraffe.presentation.ui.components.StandardButtonWithoutPadding
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.utils.getRawResourceId
import java.io.File


@Composable
fun SpeakingTestScreen(
    viewModel: MainViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    voskViewModel: VoskViewModel = hiltViewModel(), // Add VoskViewModel
    onNavigateToScreen: (Screen) -> Unit
) {

    val context = LocalContext.current

    // Initialize Vosk model when screen is launched
    LaunchedEffect(voskViewModel) {
        voskViewModel.initModel(context)
    }

    // Get the recognized text from VoskViewModel
    val recognizedText = voskViewModel.resultText

    val gradeId = userViewModel.getGradeId()
    val topicId = userViewModel.getTopicId()
    LaunchedEffect(viewModel) {
        viewModel.getSpeakingTests(gradeId, topicId)
    }
    val tests = viewModel.getSpeakingTestsResult.collectAsState().value

    var index by remember { mutableIntStateOf(0) }
    val speakingTest = tests?.getOrNull(index)

    var showWrongDialog by remember { mutableStateOf(false) }
    var showCorrectDialog by remember { mutableStateOf(false) }
    var showRecognizedTextDialog by remember { mutableStateOf(false) } // New dialog state

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

    val recorder = remember { mutableStateOf<MediaRecorder?>(null) }
    val player = remember { mutableStateOf<MediaPlayer?>(null) }
    val audioFile = remember { File(context.cacheDir, "recorded_audio.3gp") }
    var isRecording by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }
    var hasPermission by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
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
                if (tests?.getOrNull(index + 1) != null)
                    CorrectDialog {
                        showCorrectDialog = false
                        index++
                    }
                else
                    CongratsDialog {
                        userViewModel.setSpeakingTestCompleted()
                        userViewModel.incrementLevelIndex()
                        onNavigateToScreen(Screen.Home)
                    }
            }

            // New dialog to show recognized text
            if (showRecognizedTextDialog) {
                Log.d("Vosk", "Recognized text: $recognizedText")
                RecognizedTextDialog(
                    recognizedText = recognizedText,
                    expectedText = speakingTest?.text ?: "",
                    onCorrect = {
                        showRecognizedTextDialog = false
                        showCorrectDialog = true
                    },
                    onWrong = {
                        showRecognizedTextDialog = false
                        showWrongDialog = true
                    },
                    onDismiss = { showRecognizedTextDialog = false }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Listening! 🎧",
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
                                getRawResourceId(context, speakingTest?.audio)
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
                                getRawResourceId(context, speakingTest?.audio)
                            )
                        mediaPlayer!!.playbackParams = mediaPlayer!!.playbackParams.setSpeed(0.5f)
                        mediaPlayer!!.seekTo(0)
                        mediaPlayer!!.start()
                    } catch (e: Exception) {
                        errorMessage = "Audio file not found"
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.padding(bottom = 64.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SoundCard(
                    iconRes = R.drawable.ic_listen,
                    size = 72.dp
                ) {
                    if (isPlaying) {
                        player.value?.stop()
                        player.value?.release()
                        player.value = null
                        isPlaying = false
                    } else {
                        player.value = MediaPlayer().apply {
                            setDataSource(audioFile.absolutePath)
                            prepare()
                            start()
                            setOnCompletionListener {
                                isPlaying = false
                            }
                        }
                        isPlaying = true
                    }
                }
                SoundCard(
                    iconRes = R.drawable.ic_mic,
                    size = 132.dp,
                    isSelected = isRecording
                ) {
                    if (isRecording) {
                        recorder.value?.apply {
                            stop()
                            release()
                        }
                        recorder.value = null
                        isRecording = false
                        // Stop Vosk recognition when recording stops
                        voskViewModel.stopRecognition()
                    } else {
                        voskViewModel.recognizeMicrophone()
                        recorder.value = MediaRecorder().apply {
                            setAudioSource(MediaRecorder.AudioSource.MIC)
                            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                            setOutputFile(audioFile.absolutePath)
                            prepare()
                            start()
                        }
                        isRecording = true
                        // Start Vosk recognition when recording starts
                    }
                }
            }
            StandardButtonWithoutPadding(
                modifier = Modifier.padding(bottom = 24.dp),
                text = "Check",
                enabled = !isRecording
            ) {
                // Stop recording if it's still active
                if (isRecording) {
                    recorder.value?.apply {
                        stop()
                        release()
                    }
                    recorder.value = null
                    isRecording = false
                    voskViewModel.stopRecognition()
                }

                // Show the recognized text dialog
                showRecognizedTextDialog = true
            }
        }
    }
}


@Composable
fun RecognizedTextDialog(
    recognizedText: String,
    expectedText: String,
    onCorrect: () -> Unit,
    onWrong: () -> Unit,
    onDismiss: () -> Unit
) {
    val similarity = calculateTextSimilarity(recognizedText.toLowerCase(), expectedText.toLowerCase())
    val isCorrect = similarity >= 0.7 // Consider 70% similarity as correct

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your answer",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = recognizedText.ifEmpty { "No speech detected" },
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Expected",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = expectedText,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = if (isCorrect) onCorrect else onWrong,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isCorrect) Color.Green else Color.Red
                    )
                ) {
                    Text(
                        text = if (isCorrect) "Correct! Continue" else "Try Again",
                        color = Color.White
                    )
                }
            }
        }
    }
}

// A simple function to calculate text similarity (Levenshtein distance-based)
fun calculateTextSimilarity(text1: String, text2: String): Float {
    if (text1.isEmpty() || text2.isEmpty()) return 0f

    val distance = levenshteinDistance(text1, text2)
    val maxLength = maxOf(text1.length, text2.length)

    return 1 - (distance.toFloat() / maxLength)
}

// Levenshtein distance algorithm to measure text similarity
fun levenshteinDistance(s1: String, s2: String): Int {
    val m = s1.length
    val n = s2.length
    val dp = Array(m + 1) { IntArray(n + 1) }

    for (i in 0..m) {
        dp[i][0] = i
    }

    for (j in 0..n) {
        dp[0][j] = j
    }

    for (i in 1..m) {
        for (j in 1..n) {
            dp[i][j] = if (s1[i - 1] == s2[j - 1]) {
                dp[i - 1][j - 1]
            } else {
                minOf(dp[i - 1][j - 1], minOf(dp[i][j - 1], dp[i - 1][j])) + 1
            }
        }
    }

    return dp[m][n]
}