package com.imax.giraffe.presentation.screen

import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.ui.theme.mainTypography
import com.imax.giraffe.presentation.ui.theme.primaryColor

@Composable
fun SpeakingExplanationScreen(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onNavigateToScreen: (Screen) -> Unit
) {

    val context = LocalContext.current

    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

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

    LaunchedEffect(Unit) {
        val player = MediaPlayer()
        mediaPlayer = player

        try {
            val filename = "android.resource://${context.packageName}/raw/speaking"
            player.setDataSource(context, Uri.parse(filename))
            player.prepare()
            player.playbackParams = player.playbackParams.setSpeed(1f)
            player.seekTo(0)
            player.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_STOP) {
            mediaPlayer?.let { player ->
                try {
                    if (player.isPlaying) {
                        player.stop()
                    }
                    player.release()
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                }
            }
            mediaPlayer = null
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.let {
                try {
                    it.stop()
                    it.release()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            mediaPlayer = null
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.background3),
                contentScale = ContentScale.Crop
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 24.dp, top = 72.dp),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                bottomStart = 16.dp
            ),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Speaking! \uD83C\uDF99\n" +
                                "\n" +
                                "Here you can improve your understanding of English by solving exciting tasks. That's how it works:\n" +
                                "\n" +
                                "1. Listen to the audio.\n" +
                                "2. Understand the audio.\n" +
                                "3. Repeat it correctly.\n" +
                                "\n" +
                                "It's not only interesting, but it will also help you understand English better by speaking!\n" +
                                "\n" +
                                "I'm always here to help!\uD83D\uDE0A\n" +
                                "\n" +
                                "Are you ready to try?",
                        style = TextStyle(color = Color(0xFF333333), fontSize = 22.sp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.pic_giraffe),
                modifier = Modifier
                    .padding(start = 24.dp)
                    .size(54.dp)
                    .rotate(360f),
                contentDescription = "Giraffe"
            )
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(R.string.main_hero_name),
                style = TextStyle(
                    color = mainTypography,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Spacer(Modifier.weight(1f))
            Button(
                modifier = Modifier.padding(end = 24.dp),
                onClick = {
                    onNavigateToScreen.invoke(Screen.SpeakingTest)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                Text(
                    text = stringResource(R.string.skip),
                    color = Color.White,
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                )
            }
        }
    }
}
