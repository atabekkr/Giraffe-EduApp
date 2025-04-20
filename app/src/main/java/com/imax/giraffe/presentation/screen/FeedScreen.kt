package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.ui.components.StandardButtonWithoutPadding
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.primaryColor
import com.imax.giraffe.presentation.utils.LionLevelPic
import com.imax.giraffe.presentation.utils.TigerLevelPic

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit,
    onNavigateUp: () -> Unit,
) {

    var feedCount by remember { mutableIntStateOf(userViewModel.getFeedCount()) }
    var feedLevel by remember { mutableIntStateOf(userViewModel.getFeedLevel()) }

    val picAnimal = if (userViewModel.getGradeId() == 4) {
        when (feedLevel) {
            1 -> LionLevelPic.LEVEL1.resId
            2 -> LionLevelPic.LEVEL2.resId
            3 -> LionLevelPic.LEVEL3.resId
            4 -> LionLevelPic.LEVEL4.resId
            5 -> LionLevelPic.LEVEL5.resId
            6 -> LionLevelPic.LEVEL6.resId
            7 -> LionLevelPic.LEVEL7.resId
            8 -> LionLevelPic.LEVEL8.resId
            9 -> LionLevelPic.LEVEL9.resId
            else -> LionLevelPic.LEVEL10.resId
        }
    } else {
        when (feedLevel) {
            1 -> TigerLevelPic.LEVEL1.resId
            2 -> TigerLevelPic.LEVEL2.resId
            3 -> TigerLevelPic.LEVEL3.resId
            4 -> TigerLevelPic.LEVEL4.resId
            5 -> TigerLevelPic.LEVEL5.resId
            6 -> TigerLevelPic.LEVEL6.resId
            7 -> TigerLevelPic.LEVEL7.resId
            8 -> TigerLevelPic.LEVEL8.resId
            9 -> TigerLevelPic.LEVEL9.resId
            else -> TigerLevelPic.LEVEL10.resId
        }
    }

    val buttonLabel = if (feedLevel == 10) "Finish" else "Feed"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.background3),
                contentScale = ContentScale.Crop
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp)) // Rounded corners
                        .background(primaryColor) // Background color
                        .clickable { onNavigateToScreen.invoke(Screen.Topic) }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.White,
                        modifier = Modifier.size(34.dp)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Lion",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Icon(
                        painterResource(R.drawable.ic_edit),
                        contentDescription = "Edit",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp)
                    )
                }
                Text(
                    text = "level $feedLevel",
                    fontSize = 16.sp,
                    color = grayTypography
                )

                Spacer(modifier = Modifier.height(20.dp))

                Image(
                    painter = painterResource(picAnimal),
                    contentDescription = "Lion",
                    modifier = Modifier.size(300.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Счетчик
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .width(80.dp)
                        .height(96.dp)
                        .clip(RoundedCornerShape(12.dp)) // Rounded corners
                        .background(Color(0xFFFFF1CF)) // Background color
                        .clickable { /* TODO: Handle click */ }
                        .border(
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(width = 2.dp, color = Color(0xFFFFBF08))
                        )
                ) {
                    Text(
                        text = "$feedCount",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFFBF08)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                StandardButtonWithoutPadding(
                    modifier = Modifier.padding(bottom = 32.dp),
                    buttonLabel
                ) {
                    feedLevel += feedCount
                    feedCount = 0
                    userViewModel.resetFeedCount()
                    if (feedLevel > 10) {
                        onNavigateToScreen(Screen.SetNameToPet)
                    } else {
                        userViewModel.setFeedLevel(feedLevel)
                    }
                }
            }
        }
    }
}
