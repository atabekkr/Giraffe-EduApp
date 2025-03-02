package com.imax.giraffe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.imax.giraffe.presentation.screen.ChooseGradeExplanationScreen
import com.imax.giraffe.presentation.screen.ChooseGradeScreen
import com.imax.giraffe.presentation.screen.FeedScreen
import com.imax.giraffe.presentation.screen.HomeScreen
import com.imax.giraffe.presentation.screen.ListeningTestScreen
import com.imax.giraffe.presentation.screen.LoginScreen
import com.imax.giraffe.presentation.screen.WelcomeScreen
import com.imax.giraffe.presentation.screen.WritingTestScreen
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object Welcome : Screen()

    @Serializable
    data object Login : Screen()

    @Serializable
    data object ChooseGradeExplanation : Screen()

    @Serializable
    data object ChooseGrade : Screen()

    @Serializable
    data object Home : Screen()

    @Serializable
    data object Feed : Screen()

    @Serializable
    data object ListeningTest : Screen()

    @Serializable
    data object WritingTest : Screen()
}

@Composable
fun MainNav(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Screen.WritingTest
    ) {
        composable<Screen.Welcome> {
            WelcomeScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.Login> {
            LoginScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.ChooseGradeExplanation> {
            ChooseGradeExplanationScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.ChooseGrade> {
            ChooseGradeScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.Home> {
            HomeScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.Feed> {
            FeedScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.ListeningTest> {
            ListeningTestScreen()
        }
        composable<Screen.WritingTest> {
            WritingTestScreen()
        }
    }
}