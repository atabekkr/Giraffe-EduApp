package com.imax.giraffe.presentation.screen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
import com.imax.giraffe.R
import com.imax.giraffe.presentation.ui.components.SentenceCard
import com.imax.giraffe.presentation.ui.components.SoundCard
import com.imax.giraffe.presentation.ui.components.StandardButtonWithoutPadding
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.primaryColor

//@Composable
//fun ListeningTestScreen() {
//    AndroidView(
//        modifier = Modifier.fillMaxSize(),
//        factory = { context ->
//            FrameLayout(context).apply {
//                id = View.generateViewId() // Генерируем уникальный ID для контейнера фрагмента
//                (context as? AppCompatActivity)?.supportFragmentManager?.commit {
//                    replace(id, ListeningTestFragment())
//                }
//            }
//        }
//    )
//}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListeningTestScreen() {
    var selectedWords = remember {
        mutableStateListOf(
            "Lions",
            "live",
            "in",
            "groups",
            "called",
            "prides."
        )
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
        // Верхний заголовок
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
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

            }
            SoundCard(iconRes = R.drawable.ic_slow_sound, size = 96.dp) {

            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        SentenceCard(
            selectedWords
        ) {
            selectedWords.clear()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопки со словами
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(
                "Lion",
                "live",
                "group",
                "groups",
                "called",
                "in",
                "prides.",
                "at"
            ).forEach { word ->
                Button(
                    onClick = {
                        selectedWords.add(word)
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
            modifier = Modifier.padding(bottom = 24.dp),
            text = "Check"
        ) { }
    }
}