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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.ui.components.StandardButtonWithoutPadding
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.primaryColor
import com.imax.giraffe.presentation.utils.FoxLevelPic
import com.imax.giraffe.presentation.utils.LionLevelPic
import com.imax.giraffe.presentation.utils.RabbitLevelPic
import com.imax.giraffe.presentation.utils.TigerLevelPic

@Composable
fun LevelFinishedScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    petName: String,
    onNavigateToScreen: (Screen) -> Unit,
) {

    val picAnimal = when (userViewModel.getGradeId()) {

        1 -> {
            RabbitLevelPic.LEVEL10.resId
        }

        2 -> {
            FoxLevelPic.LEVEL10.resId
        }

        3 -> {
            TigerLevelPic.LEVEL10.resId
        }

        else -> {
            LionLevelPic.LEVEL10.resId
        }
    }


    val animalPic =
        if (userViewModel.getGradeId() != 1) R.drawable.pic_meal else R.drawable.pic_carrot

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
                        .clickable {
                            onNavigateToScreen.invoke(Screen.ChooseGrade)
                        }
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
                        text = petName,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
//                    Icon(
//                        painterResource(R.drawable.ic_edit),
//                        contentDescription = "Edit",
//                        modifier = Modifier
//                            .size(24.dp)
//                            .padding(start = 4.dp)
//                    )
                }
                Text(
                    text = "level 10",
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
//                        .width(80.dp)
                        .height(96.dp)
                        .clip(RoundedCornerShape(12.dp)) // Rounded corners
                        .background(Color(0xFFFFF1CF)) // Background color
                        .clickable { /* TODO: Handle click */ }
                        .border(
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(width = 2.dp, color = Color(0xFFFFBF08))
                        )
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 24.dp)
                    ) {
                        Image(
                            contentDescription = "meal",
                            modifier = Modifier.size(48.dp),
                            painter = painterResource(animalPic)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                StandardButtonWithoutPadding(
                    modifier = Modifier.padding(bottom = 32.dp),
                    "Full"
                ) {
                    onNavigateToScreen.invoke(Screen.ChooseGrade)
                }
            }
        }
    }

}