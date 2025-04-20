package com.imax.giraffe.presentation.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imax.giraffe.R
import com.imax.giraffe.presentation.models.ContentItem
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.ui.components.YouTubeCard
import com.imax.giraffe.presentation.ui.theme.mainTypography
import com.imax.giraffe.presentation.utils.toContentResponseOrNull

@Composable
fun TopicOverviewScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit
) {

    val context = LocalContext.current

    val gradeId = userViewModel.getGradeId()
    val topicId = userViewModel.getTopicId()
    LaunchedEffect(mainViewModel) {
        mainViewModel.getTopicContent(
            gradeId,
            topicId
        )
    }

    val topicOverview = mainViewModel.getTopicContentResult.collectAsState().value
    val content = topicOverview?.content

    val contentResponse = content.toContentResponseOrNull()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.background2),
                contentScale = ContentScale.Crop
            )
            .padding(16.dp),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            Text(
                modifier = Modifier
                    .padding(top = 36.dp),
                text = topicOverview?.title.toString(),
                style = TextStyle(
                    color = mainTypography,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
        item {
            YouTubeCard(
                videoId = topicOverview?.video_id.toString(), // Замени на нужное тебе видео
            )
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .clip(RoundedCornerShape(20.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 26.dp)
                        .align(Alignment.CenterHorizontally),
                    text = topicOverview?.topic_label.toString(),
                    style = TextStyle(
                        color = mainTypography,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp, start = 20.dp),
                    text = topicOverview?.topic_label2.toString(),
                    style = TextStyle(
                        color = mainTypography,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
                contentResponse?.content?.forEach { item ->
                    ContentItemView(item)
                }
            }
        }
    }
}

@Composable
fun ContentItemView(item: ContentItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Текстовая часть
        Text(
            modifier = Modifier.padding(top = 16.dp, start = 20.dp),
            text = item.text,
            style = TextStyle(
                color = mainTypography,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            ),
        )

        // Изображение (если есть)
        item.image?.let { imageName ->
            val context = LocalContext.current
            val resourceId = context.resources.getIdentifier(
                imageName, "drawable", context.packageName
            )

            if (resourceId != 0) {
                // Если изображение найдено в ресурсах
                Image(
                    painter = painterResource(resourceId),
                    contentDescription = "Изображение для ${item.text}",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(top = 16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}