package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.ui.components.MyOutlinedTextField
import com.imax.giraffe.presentation.ui.components.StandardButton
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.mainTypography

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: (Screen) -> Unit,
) {
    Column(
        modifier.paint(
            painterResource(R.drawable.background1),
            contentScale = ContentScale.Crop
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.login_title1),
            modifier = modifier.padding(top = 150.dp),
            style = TextStyle(
                color = mainTypography,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold
            ),
        )
        Text(
            text = stringResource(R.string.login_title2),
            modifier = modifier.padding(horizontal = 40.dp, vertical = 12.dp),
            style = TextStyle(
                color = grayTypography,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            ),
            textAlign = TextAlign.Center
        )
        MyOutlinedTextField()
        Spacer(modifier = modifier.weight(1f))
        StandardButton(
            text = stringResource(R.string.save)
        ) {
            onNavigateToHome.invoke(Screen.ChooseGradeExplanation)
        }
    }
}