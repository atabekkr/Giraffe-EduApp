package com.imax.giraffe.presentation.screen

import android.util.Log
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
import androidx.compose.runtime.collectAsState
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
import com.imax.giraffe.presentation.screen.dialog.CongratsDialog
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.primaryColor
import com.imax.giraffe.presentation.utils.parseVocabularyJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MatchingScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit
) {

    val gradeId = userViewModel.getGradeId()
    val topicId = userViewModel.getTopicId()
    Log.d("Matching", gradeId.toString() + topicId.toString())
    LaunchedEffect(viewModel) {
        viewModel.getVocabulary(
            gradeId,
            topicId
        )
    }

    val vocabulary = viewModel.getVocabularyResult.collectAsState().value

    val words = parseVocabularyJson(vocabulary?.englishWords ?: "")
    val translations = parseVocabularyJson(vocabulary?.karakalpakWords ?: "")

    var selectedWord by remember { mutableStateOf<String?>(null) }
    var selectedTranslation by remember { mutableStateOf<String?>(null) }
    var matchedPairs by remember { mutableStateOf(setOf<String>()) }
    var lastMatchResult by remember {
        mutableStateOf<Pair<Pair<String, String>, Boolean>?>(
            null
        )
    }

    var showCongratsDialog by remember { mutableStateOf(false) }

    fun checkMatch(word: String? = null, translation: String? = null) {
        if (word != null) selectedWord = word
        if (translation != null) selectedTranslation = translation

        if (selectedWord != null && selectedTranslation != null) {
            val isCorrect = words.indexOf(selectedWord) == translations.indexOf(selectedTranslation)

            if (isCorrect) {
                matchedPairs += selectedWord!!
                matchedPairs += selectedTranslation!!
                if (matchedPairs.size == words.size + translations.size) {
                    showCongratsDialog = true
                }
            }

            // Store both the word and translation in lastMatchResult
            lastMatchResult = Pair(Pair(selectedWord!!, selectedTranslation!!), isCorrect)

            // Reset selections after a short delay if incorrect
            if (!isCorrect) {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(500)
                    selectedWord = null
                    selectedTranslation = null
                    lastMatchResult = null
                }
            } else {
                selectedWord = null
                selectedTranslation = null
                lastMatchResult = null
            }
        }
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

        if (showCongratsDialog) {
            CongratsDialog {
                userViewModel.incrementLevelIndex()
                onNavigateToScreen.invoke(Screen.Home)
                showCongratsDialog = false
            }
        }

        // Header
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

        // Matching Content
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 90.dp)
        ) {
            // Words Column
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                items(words.shuffled()) { word ->
                    val isMatched = matchedPairs.contains(word)
                    val isSelected = selectedWord == word
                    val isIncorrect = lastMatchResult?.let {
                        !it.second && (it.first.first == word || it.first.second == word)
                    } ?: false

                    MatchingItem(
                        text = word,
                        isSelected = isSelected,
                        isMatched = isMatched,
                        isIncorrect = isIncorrect,
                        onClick = { checkMatch(word = word) }
                    )
                }
            }

            // Translations Column
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                items(translations.shuffled()) { translation ->
                    val isMatched = matchedPairs.contains(translation)
                    val isSelected = selectedTranslation == translation
                    val isIncorrect = lastMatchResult?.let {
                        !it.second && (it.first.first == translation || it.first.second == translation)
                    } ?: false

                    MatchingItem(
                        text = translation,
                        isSelected = isSelected,
                        isMatched = isMatched,
                        isIncorrect = isIncorrect,
                        onClick = { checkMatch(translation = translation) }
                    )
                }
            }
        }
    }
}

@Composable
fun MatchingItem(
    text: String,
    isSelected: Boolean,
    isMatched: Boolean,
    isIncorrect: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        isMatched -> Color(0xFFE6F3E6)
        isIncorrect -> Color(0xFFFEE6E6)
        isSelected -> Color(0xFFF0F0F0)
        else -> Color.White
    }

    val borderColor = when {
        isMatched -> Color.Green
        isIncorrect -> Color.Red
        isSelected -> primaryColor
        else -> Color.Transparent
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, borderColor, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable(enabled = !isMatched, onClick = onClick)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp),
            style = TextStyle(color = primaryColor)
        )
    }

}