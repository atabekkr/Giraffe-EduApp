package com.imax.giraffe.presentation.screen

import android.Manifest
import android.media.MediaPlayer
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.screen.dialog.CongratsDialog
import com.imax.giraffe.presentation.screen.dialog.CorrectDialog
import com.imax.giraffe.presentation.screen.dialog.ErrorDialog
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.screen.viewmodel.VoiceViewModel
import com.imax.giraffe.presentation.ui.components.AnimatedSoundCard
import com.imax.giraffe.presentation.ui.components.SoundCard
import com.imax.giraffe.presentation.ui.components.StandardButtonWithoutPadding
import com.imax.giraffe.presentation.ui.components.TestProgress
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.utils.isTextCorrect

@Composable
fun SpeakingTestScreen(
    viewModel: MainViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    voiceViewModel: VoiceViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onNavigateToScreen: (Screen) -> Unit
) {

    val context = LocalContext.current

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

    var state = voiceViewModel.state.collectAsState()

    var mediaPlayer = remember { MediaPlayer() }

    val snackbarHostState = remember { SnackbarHostState() }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Показываем Snackbar при изменении errorMessage
    LaunchedEffect(errorMessage) {
        errorMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            errorMessage = null // Сбрасываем ошибку после показа
        }
    }

    var canRecord by remember {
        mutableStateOf(false)
    }

    var lifecycleEvent by remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            lifecycleEvent = event
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_STOP) {
            mediaPlayer.stop()
            mediaPlayer.release()
            voiceViewModel.stopListening()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        // Creates an permission request
        val recordAudioLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                canRecord = isGranted
            }
        )

        LaunchedEffect(key1 = recordAudioLauncher) {
            // Launches the permission request
            recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
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
                if (tests?.getOrNull(index + 1) != null)
                    CorrectDialog {
                        showCorrectDialog = false
                        index++
                    }
                else
                    CongratsDialog {
                        userViewModel.setSpeakingTestCompleted()
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
                        text = "Speaking! \uD83C\uDF99",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Boost your speaking with Saribek.",
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
                modifier = Modifier.padding(top = 42.dp, start = 12.dp, end = 12.dp),
                currentQuestion = index + 1,
                totalQuestions = tests?.size ?: 0
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, top = 26.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SoundCard(iconRes = R.drawable.ic_sound, size = 132.dp) {
                    try {
                        mediaPlayer.reset()
                        speakingTest?.audio?.let { audioFileName ->
                            val filename =
                                "android.resource://" + context.packageName + "/raw/$audioFileName";
                            mediaPlayer.setDataSource(context, Uri.parse(filename))
                            mediaPlayer.prepare()
                            mediaPlayer.playbackParams = mediaPlayer.playbackParams?.setSpeed(1f)
                                ?: mediaPlayer.playbackParams
                            mediaPlayer.seekTo(0)
                            mediaPlayer.start()
                        } ?: run {
                            errorMessage = "Audio file name is null"
                        }
                    } catch (e: Exception) {
                        errorMessage = "Error playing audio"
                    }
                }
                SoundCard(iconRes = R.drawable.ic_slow_sound, size = 96.dp) {
                    try {
                        mediaPlayer.reset()
                        speakingTest?.audio?.let { audioFileName ->
                            val filename =
                                "android.resource://" + context.packageName + "/raw/$audioFileName";
                            mediaPlayer.setDataSource(context, Uri.parse(filename))
                            mediaPlayer.prepare()
                            mediaPlayer.playbackParams = mediaPlayer.playbackParams?.setSpeed(0.5f) ?: mediaPlayer.playbackParams
                            mediaPlayer.seekTo(0)
                            mediaPlayer.start()
                        } ?: run {
                            errorMessage = "Audio file name is null"
                        }
                    } catch (e: Exception) {
                        errorMessage = "Error playing slow audio"
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.padding(bottom = 64.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AnimatedSoundCard(
                    iconRes = R.drawable.ic_mic,
                    size = 132.dp,
                    isSelected = state.value.isSpeaking
                ) {
                    if (canRecord) {
                        if (state.value.isSpeaking) {
                            voiceViewModel.stopListening()
                        } else {
                            voiceViewModel.startListening("en")
                        }
                    }
                }
            }
            StandardButtonWithoutPadding(
                modifier = Modifier.padding(bottom = 48.dp),
                text = if (state.value.spokenText.isNotBlank()) "Check" else "Start record audio",
                enabled = state.value.spokenText.isNotBlank()
            ) {
                if (state.value.spokenText.isNotBlank()) {
                    if (isTextCorrect(
                            recognizedText = state.value.spokenText,
                            correctAnswer = speakingTest?.text.toString()
                        )
                    ) {
                        showCorrectDialog = true
                    } else {
                        showWrongDialog = true
                    }
                    voiceViewModel.setDefaultText()
                } else {
                    errorMessage = "Please start recording audio"
                }
            }
        }
    }

}