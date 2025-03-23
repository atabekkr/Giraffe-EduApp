package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.primaryColor
import kotlinx.coroutines.delay

@Composable
fun MatchingScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit
) {
    val words = listOf("lion", "tiger", "fox", "rabbit", "giraffe", "kangaroo")
    val translations = listOf("жолбарыс", "арыстан", "қоян", "түлкі", "жираф", "кенгуру")

    var selectedWord by remember { mutableStateOf<String?>(null) }
    var selectedTranslation by remember { mutableStateOf<String?>(null) }
    var matchResult by remember { mutableStateOf<Pair<String, Boolean>?>(null) }

    fun checkMatch(word: String? = null, translation: String? = null) {
        if (word != null) selectedWord = word
        if (translation != null) selectedTranslation = translation

        if (selectedWord != null && selectedTranslation != null) {
            val isCorrect = words.indexOf(selectedWord) == translations.indexOf(selectedTranslation)
            matchResult = Pair(selectedWord!!, isCorrect) // Теперь selectedWord точно не null
        }
    }


    LaunchedEffect(matchResult) {
        delay(2000)
        selectedWord = null
        selectedTranslation = null
        matchResult = null
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.background2),
                contentScale = ContentScale.Crop
            )
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Vocabulary! ⚡\uFE0F",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    text = "Boost your listening with Saribek.",
                    modifier = Modifier.padding(top = 6.dp),
                    style = TextStyle(
                        color = grayTypography,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Image(
                painter = painterResource(R.drawable.pic_giraffe),
                modifier = Modifier.size(54.dp),
                contentDescription = "Giraffe"
            )
        }

        Row(modifier = Modifier.fillMaxSize().padding(top = 90.dp, start = 16.dp, end = 16.dp)) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(words) { word ->
                    val borderColor = when {
                        matchResult?.first == word -> if (matchResult?.second == true) Color.Green else Color.Red
                        selectedWord == word -> Color.Yellow
                        else -> Color.Transparent
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .border(2.dp, borderColor, RoundedCornerShape(8.dp))
                            .clickable { checkMatch(word = word) }
                    ) {
                        Text(text = word, fontSize = 20.sp, modifier = Modifier.padding(16.dp), style = TextStyle(color = primaryColor))
                    }
                }
            }

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(translations) { translation ->
                    val borderColor = when {
                        matchResult?.first == translation -> if (matchResult?.second == true) Color.Green else Color.Red
                        selectedTranslation == translation -> Color.Yellow
                        else -> Color.Transparent
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .border(2.dp, borderColor, RoundedCornerShape(8.dp))
                            .clickable { checkMatch(translation = translation) }
                    ) {
                        Text(text = translation, fontSize = 20.sp, modifier = Modifier.padding(16.dp), style = TextStyle(color = primaryColor))
                    }
                }
            }
        }
    }
}
