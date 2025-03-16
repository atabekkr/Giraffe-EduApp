package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.ui.components.ReadingSentenceCard
import com.imax.giraffe.presentation.ui.components.StandardButtonWithoutPadding
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.primaryColor

@Composable
fun ReadingTestScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit
) {

    LaunchedEffect(viewModel) {
        viewModel.getReadingTests(1, 2)
    }
    val tests = viewModel.getReadingTestsResult.collectAsState().value

    var index by remember { mutableIntStateOf(0) }
    val readingTest = tests?.getOrNull(index)
    val readingText = "${readingTest?.firstPart} _____ ${readingTest?.secondPart}"

    val annotatedString = buildAnnotatedString {
        append(readingTest?.firstPart ?: "")
        appendInlineContent("gap", " ")
        append(readingTest?.secondPart ?: "")
    }

    val inlineContent = mapOf(
        "gap" to InlineTextContent(
            Placeholder(50.sp, 18.sp, PlaceholderVerticalAlign.TextBottom) // Выравниваем по нижней границе текста
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 2.dp) // Поднимаем линию ближе к тексту
            ) {
                Divider(
                    color = Color.Gray,
                    thickness = 2.dp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )

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
                    text = "Reading! 🎧",
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

        ReadingSentenceCard(
            modifier = Modifier.padding(top = 56.dp),
            firstPart = readingTest?.firstPart,
            secondPart = readingTest?.secondPart
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    modifier = Modifier
                        .height(64.dp)
                        .weight(1f),
                    onClick = {
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text(
                        text = "in",
                        color = primaryColor,
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    )
                }
                Button(
                    modifier = Modifier
                        .height(64.dp)
                        .weight(1f),
                    onClick = {
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text(
                        text = "under",
                        color = primaryColor,
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    modifier = Modifier
                        .height(64.dp)
                        .weight(1f),
                    onClick = {
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text(
                        text = "at",
                        color = primaryColor,
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    )
                }
                Button(
                    modifier = Modifier
                        .height(64.dp)
                        .weight(1f),
                    onClick = {
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text(
                        text = "on",
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