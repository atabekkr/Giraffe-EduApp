package com.imax.giraffe.presentation.ui.components

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.imax.giraffe.R
import com.imax.giraffe.presentation.ui.theme.primaryColor
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun SoundCard(
    modifier: Modifier = Modifier,
    iconRes: Int,
    size: Dp,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val iconSize = if (size == 72.dp) 36.dp else 64.dp

    Card(
        modifier = modifier
            .size(size)
            .border(
                width = if (isSelected) 4.dp else 0.dp, // Добавляем бордер при выборе
                color = if (isSelected) primaryColor else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            ),
        onClick = { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = "Sound Icon",
                tint = primaryColor,
                modifier = Modifier.size(iconSize)
            )
        }
    }
}

@Composable
fun AnimatedSoundCard(
    modifier: Modifier = Modifier,
    iconRes: Int,
    size: Dp,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val iconSize = if (size == 72.dp) 36.dp else 64.dp

    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isSelected) 1.15f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val ringAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200),
            repeatMode = RepeatMode.Restart
        )
    )

    val ringScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer {
                        scaleX = ringScale
                        scaleY = ringScale
                        alpha = ringAlpha
                    }
                    .background(
                        color = primaryColor,
                        shape = CircleShape
                    )
            )
        }

        Card(
            modifier = Modifier
                .size(size)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
                .border(
                    width = if (isSelected) 3.dp else 0.dp,
                    color = if (isSelected) primaryColor else Color.Transparent,
                    shape = RoundedCornerShape(20.dp)
                ),
            shape = RoundedCornerShape(20.dp),
            onClick = onClick,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = "Mic Icon",
                    tint = primaryColor,
                    modifier = Modifier.size(iconSize)
                )
            }
        }
    }
}

@Composable
fun SentenceCard(
    modifier: Modifier = Modifier,
    selectedWords: List<String>,
    onClear: (word: String) -> Unit
) {
    Card(
        modifier = modifier
            .width(342.dp)
            .height(142.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp, end = 15.dp),
            contentAlignment = Alignment.TopEnd // Размещаем кнопку в верхнем правом углу
        ) {
            OutlinedButton(
                onClick = { selectedWords.lastOrNull()?.let { onClear(it) } },
                modifier = Modifier.size(24.dp),
                shape = CircleShape,
                border = BorderStroke(width = 0.dp, primaryColor),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = primaryColor)
            ) {
                Icon(
                    Icons.Default.Close,
                    modifier = Modifier.padding(4.dp),
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            // Текст, формируемый из выбранных слов
            Text(
                text = selectedWords.joinToString(" "),
                fontSize = 20.sp,
                lineHeight = 36.sp,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Разделители
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(32.dp))
                Divider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(36.dp))
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun ReadingSentenceCard(
    modifier: Modifier = Modifier,
    firstPart: String?,
    secondPart: String?
) {
    Card(
        modifier = modifier
            .width(342.dp)
            .height(142.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 30.dp)
        ) {
            ReadingText(firstPart, secondPart)
        }
    }
}

@Composable
fun ReadingText(firstPart: String?, secondPart: String?) {
    val underlineWidth = 50.sp // Можно менять ширину подчёркивания

    val annotatedString = buildAnnotatedString {
        append(firstPart ?: "")
        appendInlineContent("gap", " ")
        append(secondPart ?: "")
    }

    val inlineContent = mapOf(
        "gap" to InlineTextContent(
            Placeholder(
                underlineWidth,
                2.sp,
                PlaceholderVerticalAlign.TextBottom
            ) // Ставим линию между текстом
        ) {
            Canvas(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .width(50.dp)
                    .height(2.dp)
            ) {
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = size.height
                )
            }
        }
    )

    Text(
        text = annotatedString,
        inlineContent = inlineContent,
        fontSize = 20.sp,
        lineHeight = 36.sp,
        maxLines = 2,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}
@Composable
fun YouTubePlayer(
    videoId: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            val youTubePlayerView = YouTubePlayerView(ctx)
            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(player: YouTubePlayer) {
                    player.loadVideo(videoId, 0f)
                }
            })
            lifecycleOwner.lifecycle.addObserver(youTubePlayerView)
            youTubePlayerView
        },
        update = {}
    )

    // Освобождаем ресурсы, когда уходим с экрана
    DisposableEffect(Unit) {
        onDispose {
            Log.d("YouTube", "Disposing player")
            // Это может быть освобождение в зависимости от твоей библиотеки
            // Например, youTubePlayerView.release() или аналог
        }
    }
}

@Composable
fun YouTubeCard(videoId: String) {
    var playVideo by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(
                width = 3.dp,
                color = Color(0xFFFFBF08), // жёлтая рамка
                shape = RoundedCornerShape(20.dp)
            )
            .background(Color.White)
            .clickable { playVideo = true }
    ) {
        if (playVideo) {
            YouTubePlayer(
                videoId = videoId,
                modifier = Modifier
                    .fillMaxSize()
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.ic_play_youtube),
                contentDescription = "Play",
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Center),
                tint = Color(0xFFFFBF08)
            )
        }
    }
}
