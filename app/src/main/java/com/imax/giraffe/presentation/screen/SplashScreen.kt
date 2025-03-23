package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.screen.viewmodel.SplashViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit
) {
    val lifeCycleOwner = LocalLifecycleOwner.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.pic_splash_bg),
                contentScale = ContentScale.Crop
            )
            .padding(horizontal = 24.dp)
    ) {
        LaunchedEffect(lifeCycleOwner) {
            delay(1000)
            onNavigateToScreen.invoke(Screen.Welcome)
//            if (viewModel.isLogin())
//                onNavigateToScreen.invoke(Screen.Welcome)
//            else
//                onNavigateToScreen.invoke(Screen.Topic)
        }
    }

}