package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.ui.theme.mainTypography
import com.imax.giraffe.presentation.ui.theme.primaryColor

@Composable
fun AfterGradeChooseScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit
) {

    val name = viewModel.getUserName()

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
                        "You're in the prime\n" +
                                "of your life, $name!\uD83D\uDCAA",
                        style = TextStyle(color = Color(0xFF333333), fontSize = 22.sp)
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp),
                    text = "It's a time when so many interesting things are happening: new subjects, favorite teachers, and, of course, loyal friends",
                    style = TextStyle(color = Color(0xFF333333), fontSize = 22.sp)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp),
                    text = "There you have your own pet a lion. You will earn meat for having success in your tests and you can raise and take care of your pet. \uD83E\uDD81",
                    style = TextStyle(color = Color(0xFF333333), fontSize = 22.sp)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp),
                    text = "So, are you ready to start your journey to success in learning English? ⚡\uFE0F",
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
                text = stringResource(R.string.main_hero_name),
                style = TextStyle(
                    color = mainTypography,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Spacer(Modifier.weight(1f))
            Button(
                modifier = Modifier.padding(end = 24.dp),
                onClick = {
                    onNavigateToScreen.invoke(Screen.Topic)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                Text(
                    text = stringResource(R.string.skip),
                    color = Color.White,
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                )
            }
        }
    }
}
