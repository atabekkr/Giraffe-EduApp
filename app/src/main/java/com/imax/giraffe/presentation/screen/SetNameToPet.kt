package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.screen.dialog.LevelFinishedDialog
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.ui.components.PetNameTextField
import com.imax.giraffe.presentation.ui.components.StandardButtonWithoutPadding
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.mainTypography

@Composable
fun SetNameToPetScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel(),
    onNavigateToHome: (Screen) -> Unit,
) {

    var saveButtonEnabled by rememberSaveable { mutableStateOf(false) }
    var name by rememberSaveable { mutableStateOf("") }

    var showCongratsDialog by remember { mutableStateOf(false) }

    Column(
        modifier
            .paint(
                painterResource(R.drawable.background1),
                contentScale = ContentScale.Crop
            )
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (showCongratsDialog) {
            LevelFinishedDialog {
                viewModel.setCompletedStatus()
                onNavigateToHome.invoke(Screen.LevelFinished(name))
                showCongratsDialog = false
            }
        }

        Text(
            text = "What do you want to\n" +
                    "name your pet?",
            modifier = modifier.padding(top = 150.dp),
            style = TextStyle(
                color = mainTypography,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold
            ),
        )
        Text(
            text = "Please write a name for your pet so that we can get to know each other better!",
            modifier = modifier.padding(horizontal = 40.dp, vertical = 12.dp),
            style = TextStyle(
                color = grayTypography,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            ),
            textAlign = TextAlign.Center
        )
        PetNameTextField {
            name = it
            saveButtonEnabled = name.length >= 3
        }
        Spacer(modifier = modifier.weight(1f))
        StandardButtonWithoutPadding(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 48.dp),
            text = stringResource(R.string.save),
            enabled = saveButtonEnabled
        ) {
            showCongratsDialog = true
        }
    }
}