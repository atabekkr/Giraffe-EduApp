package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.ui.theme.disabledButton

@Composable
fun GetStartedScreen(
    modifier: Modifier = Modifier,
    onNavigateToScreen: (Screen) -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.pic_get_started),
                contentScale = ContentScale.Crop
            )
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(64.dp)
                .align(Alignment.BottomCenter),
            onClick = {
                onNavigateToScreen.invoke(Screen.Welcome)
            },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color(0xffFF5F3D),
                disabledContainerColor = disabledButton,
                disabledContentColor = Color.White
            )
        ) {
            Text("Get Started", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
        }
    }
}