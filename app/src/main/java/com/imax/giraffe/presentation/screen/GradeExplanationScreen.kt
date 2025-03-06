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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.ui.theme.primaryColor

@Composable
fun ChooseGradeExplanationScreen(
    modifier: Modifier = Modifier,
    onNavigateToScreen: (Screen) -> Unit
) {
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
                        "Hello, my friend! \uD83D\uDC4B",
                        style = TextStyle(color = Color(0xFF333333), fontSize = 22.sp)
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(12.dp)) // Rounded corners
                            .background(primaryColor) // Background color
                            .clickable {
                                onNavigateToScreen.invoke(Screen.ChooseGrade)
                            }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = "Avatar",
                            modifier = Modifier.size(32.dp),
                            tint = Color.White
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    text = "I am Saribek, your faithful assistant in this application. I will accompany you, prompt and help you at every step.",
                    style = TextStyle(color = Color(0xFF333333), fontSize = 22.sp)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp),
                    text = "I am Saribek, your faithful assistant in this application. I will accompany you, prompt and help you at every step.",
                    style = TextStyle(color = Color(0xFF333333), fontSize = 22.sp)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp),
                    text = "What's your name? \uD83E\uDD14",
                    style = TextStyle(color = Color(0xFF333333), fontSize = 22.sp)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp),
                    text = stringResource(R.string.login_title2),
                    style = TextStyle(color = Color(0xFF333333), fontSize = 22.sp)
                )
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
                text = "Giraffe Saribek!",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }
}
