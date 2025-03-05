package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imax.giraffe.R
import com.imax.giraffe.presentation.ui.components.SoundCard
import com.imax.giraffe.presentation.ui.components.StandardButtonWithoutPadding
import com.imax.giraffe.presentation.ui.theme.grayTypography

@Composable
fun SpeakingTestScreen() {
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 48.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SoundCard(iconRes = R.drawable.ic_sound, size = 132.dp) {

            }
            SoundCard(iconRes = R.drawable.ic_slow_sound, size = 96.dp) {

            }
        }

        Spacer(modifier = Modifier.weight(1f))
        SoundCard(modifier = Modifier.padding(bottom = 64.dp), iconRes = R.drawable.ic_mic, size = 132.dp) {

        }
        StandardButtonWithoutPadding(
            modifier = Modifier.padding(bottom = 24.dp),
            text = "Check"
        ) { }
    }
}