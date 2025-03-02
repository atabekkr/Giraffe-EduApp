package com.imax.giraffe.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.imax.giraffe.presentation.navigation.MainNav
import com.imax.giraffe.presentation.ui.theme.GiraffeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        setContent {
            GiraffeTheme(darkTheme = false) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    MainContent()
                }
            }
        }
    }

    @Composable
    fun MainContent(modifier: Modifier = Modifier) {
        MainNav(modifier = modifier, navHostController = rememberNavController())
    }
}