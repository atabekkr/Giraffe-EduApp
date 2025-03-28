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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
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
import com.imax.giraffe.presentation.ui.components.SoundCard
import com.imax.giraffe.presentation.ui.components.StandardButtonWithoutPadding
import com.imax.giraffe.presentation.ui.components.WritingTestInput
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.utils.getRawResourceId

@Composable
fun WritingTestScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.getWritingTests(1, 1)
    }
    val tests = viewModel.getWritingTestsResult.collectAsState().value

    var index by remember { mutableIntStateOf(0) }
    val writingTest = tests?.getOrNull(index)
    var inputText by remember { mutableStateOf("") }

    var showWrongDialog by remember { mutableStateOf(false) }
    var showCorrectDialog by remember { mutableStateOf(false) }

    var isPlaying by remember { mutableStateOf(false) }

    var currentAudio by remember { mutableStateOf("test") } // Название ресурса
    val audioResId = getRawResourceId(currentAudio)
    val mediaPlayer = rememberMediaPlayer(audioResId)

    // Обязательно освобождаем ресурсы
    DisposableEffect(mediaPlayer) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    Column(
        modifier = modifier
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
                    inputText = ""
                    index++
                }
            else
                CongratsDialog {
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

        // Кнопки с иконками
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 48.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SoundCard(iconRes = R.drawable.ic_sound, size = 132.dp) {
                // Сбрасываем скорость до normal
                val playbackParams = mediaPlayer!!.playbackParams
                playbackParams.speed = 1f
                mediaPlayer.playbackParams = playbackParams

                // Перематываем в начало и запускаем
                mediaPlayer.seekTo(0)
                mediaPlayer.start()
            }
            SoundCard(iconRes = R.drawable.ic_slow_sound, size = 96.dp) {
                // Сбрасываем скорость до normal
                val playbackParams = mediaPlayer!!.playbackParams
                playbackParams?.speed = 0.5f
                mediaPlayer?.playbackParams = playbackParams

                // Перематываем в начало и запускаем
                mediaPlayer.seekTo(0)
                mediaPlayer.start()
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

@Composable
fun rememberMediaPlayer(audioResId: Int?): MediaPlayer? {
    val context = LocalContext.current
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    val currentAudioResId by rememberUpdatedState(audioResId)

    LaunchedEffect(currentAudioResId) {
        mediaPlayer?.release() // Освобождаем предыдущий плеер перед созданием нового
        if (currentAudioResId != null && currentAudioResId != 0) {
            mediaPlayer = MediaPlayer.create(context, currentAudioResId!!).apply {
                setOnCompletionListener {
                    it.release()
                    mediaPlayer = null
                }
            }
        }
    }

    return mediaPlayer
}