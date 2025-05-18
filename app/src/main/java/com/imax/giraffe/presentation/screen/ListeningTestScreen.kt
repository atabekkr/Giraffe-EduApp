package com.imax.giraffe.presentation.screen

import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.text.TextStyle
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
import com.imax.giraffe.presentation.screen.viewmodel.GradeDataViewModel
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.ui.components.SentenceCard
import com.imax.giraffe.presentation.ui.components.SoundCard
import com.imax.giraffe.presentation.ui.components.StandardButtonWithoutPadding
import com.imax.giraffe.presentation.ui.components.TestProgress
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.mainTypography
import com.imax.giraffe.presentation.ui.theme.primaryColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListeningTestScreen(
    viewModel: MainViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    gradeDataViewModel: GradeDataViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onNavigateToScreen: (Screen) -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.getListeningTests()
    }
    val tests = viewModel.getListeningTestsResult.collectAsState().value

    var index by remember { mutableIntStateOf(0) }
    val listeningTest = tests?.getOrNull(index)
    val listeningText = listeningTest?.text?.split(" ") ?: emptyList()
    val availableWords =
        remember(listeningText) { mutableStateListOf<String>().apply { addAll(listeningText) } }
    val answer = remember { mutableStateListOf<String>() }

    var showWrongDialog by remember { mutableStateOf(false) }
    var showCorrectDialog by remember { mutableStateOf(false) }

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
            try {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                }
                mediaPlayer.release()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
        }
    }

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
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showWrongDialog) {
                ErrorDialog { showWrongDialog = false }
            }
            if (showCorrectDialog) {
                if (tests?.getOrNull(index + 1) != null)
                    CorrectDialog {
                        showCorrectDialog = false
                        answer.clear()
                        index++
                    }
                else
                    CongratsDialog {
                        gradeDataViewModel.updateListeningTestCompleted()
                        gradeDataViewModel.incrementFeedCount()
                        gradeDataViewModel.incrementTopicCompletedPercent()

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
                        text = "Listening! 🎧",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = mainTypography
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
                modifier = Modifier.padding(top = 42.dp, start = 12.dp, end = 12.dp),
                currentQuestion = index + 1,
                totalQuestions = tests?.size ?: 1
            )

            // Кнопки с иконками
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
                        listeningTest?.audio?.let { audioFileName ->
                            val filename =
                                "android.resource://" + context.packageName + "/raw/$audioFileName";
                            mediaPlayer.setDataSource(context, Uri.parse(filename))
                            mediaPlayer.prepare()
                            mediaPlayer.playbackParams = mediaPlayer.playbackParams.setSpeed(1f)
                                ?: mediaPlayer.playbackParams
                            mediaPlayer.seekTo(0)
                            mediaPlayer.start()
                        } ?: run {
                            errorMessage = "Audio file name is null"
                        }
                    } catch (e: Exception) {
                        Log.e("ListeningTest", e.localizedMessage, e)
                        errorMessage = "Error playing audio"
                    }
                }
                SoundCard(iconRes = R.drawable.ic_slow_sound, size = 96.dp) {
                    try {
                        mediaPlayer.reset()
                        listeningTest?.audio?.let { audioFileName ->
                            val filename =
                                "android.resource://" + context.packageName + "/raw/$audioFileName";
                            mediaPlayer.setDataSource(context, Uri.parse(filename))
                            mediaPlayer.prepare()
                            mediaPlayer.playbackParams = mediaPlayer.playbackParams.setSpeed(0.5f)
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
            }

            Spacer(modifier = Modifier.height(24.dp))

            SentenceCard(
                selectedWords = answer
            ) { word ->
                availableWords.add(word)
                answer.remove(word)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопки со словами
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                availableWords.shuffled().forEach { word ->
                    Button(
                        onClick = {
                            answer.add(word)
                            availableWords.remove(word)
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text(
                            text = word,
                            color = primaryColor,
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            StandardButtonWithoutPadding(
                modifier = Modifier.padding(bottom = 48.dp, top = 12.dp),
                text = "Check"
            ) {
                val correctText = answer.joinToString(" ")
                if (correctText == listeningTest?.text) showCorrectDialog = true
                else showWrongDialog = true
            }
        }
    }
}