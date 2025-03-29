package com.imax.giraffe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.imax.giraffe.presentation.screen.AfterGradeChooseScreen
import com.imax.giraffe.presentation.screen.ChooseGradeExplanationScreen
import com.imax.giraffe.presentation.screen.ChooseGradeScreen
import com.imax.giraffe.presentation.screen.FeedScreen
import com.imax.giraffe.presentation.screen.HomeScreen
import com.imax.giraffe.presentation.screen.ListeningTestScreen
import com.imax.giraffe.presentation.screen.LoginScreen
import com.imax.giraffe.presentation.screen.MatchingScreen
import com.imax.giraffe.presentation.screen.ReadingTestScreen
import com.imax.giraffe.presentation.screen.SpeakingTestScreen
import com.imax.giraffe.presentation.screen.SplashScreen
import com.imax.giraffe.presentation.screen.TopicScreen
import com.imax.giraffe.presentation.screen.WelcomeScreen
import com.imax.giraffe.presentation.screen.WritingTestScreen
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object Welcome : Screen()

    @Serializable
    data object Splash : Screen()

    @Serializable
    data object Login : Screen()

    @Serializable
    data class ChooseGradeExplanation(val name: String) : Screen()

    @Serializable
    data object ChooseGrade : Screen()

    @Serializable
    data object AfterChooseGrade : Screen()

    @Serializable
    data object Topic : Screen()

    @Serializable
    data object Matching : Screen()

    @Serializable
    data object Home : Screen()

    @Serializable
    data object Feed : Screen()

    @Serializable
    data object ListeningTest : Screen()

    @Serializable
    data object WritingTest : Screen()

    @Serializable
    data object SpeakingTest : Screen()

    @Serializable
    data object ReadingTest : Screen()
}

@Composable
fun MainNav(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Screen.Splash
    ) {
        composable<Screen.Splash> {
            SplashScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
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
        composable<Screen.ChooseGradeExplanation> { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "No message"
            ChooseGradeExplanationScreen(name = name) { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.ChooseGrade> {
            ChooseGradeScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.AfterChooseGrade> {
            AfterGradeChooseScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.Topic> {
            TopicScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.Matching> {
            MatchingScreen { navigateTo ->
                navHostController.navigate(navigateTo) {
                    popUpTo(Screen.Topic) { inclusive = false }
                }
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
            ListeningTestScreen { navigateTo ->
                navHostController.navigate(navigateTo) {
                    popUpTo(Screen.Topic) { inclusive = false }
                }
            }
        }
        composable<Screen.WritingTest> {
            WritingTestScreen { navigateTo ->
                navHostController.navigate(navigateTo) {
                    popUpTo(Screen.Topic) { inclusive = false }
                }
            }
        }
        composable<Screen.SpeakingTest> {
            SpeakingTestScreen { navigateTo ->
                navHostController.navigate(navigateTo) {
                    popUpTo(Screen.Topic) { inclusive = false }
                }
            }
        }
        composable<Screen.ReadingTest> {
            ReadingTestScreen { navigateTo ->
                navHostController.navigate(navigateTo) {
                    popUpTo(Screen.Topic) { inclusive = false }
                }
            }
        }
    }
}