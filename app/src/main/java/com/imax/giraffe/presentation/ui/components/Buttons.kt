package com.imax.giraffe.presentation.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imax.giraffe.presentation.ui.theme.disabledButton
import com.imax.giraffe.presentation.ui.theme.primaryColor
import kotlinx.coroutines.delay

@Composable
fun StandardButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    val saveButtonColor = if (enabled) primaryColor else disabledButton

    Button(
        modifier = Modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 24.dp,
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            )
            .fillMaxWidth()
            .height(56.dp),
        enabled = enabled,
        onClick = {
            onClick.invoke()
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = saveButtonColor,
            disabledContainerColor = disabledButton,
            disabledContentColor = Color.White
        )
    ) {
        Text(text, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
    }
}

@Composable
fun StandardButtonWithoutPadding(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val saveButtonColor = if (enabled) primaryColor else disabledButton
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        onClick = {
            onClick.invoke()
        },
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = saveButtonColor,
            disabledContainerColor = disabledButton,
            disabledContentColor = Color.White
        )
    ) {
        Text(text, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
    }
}

@Composable
fun StandardButtonForCongratsDialog(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val saveButtonColor = if (enabled) primaryColor else disabledButton

    var isClickable by remember { mutableStateOf(true) }
    var clicked by remember { mutableStateOf(false) }

    // Это сработает при клике, чтобы сбросить блокировку через 2 секунды
    LaunchedEffect(clicked) {
        if (clicked) {
            delay(2000L)
            isClickable = true
            clicked = false
        }
    }

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        onClick = {
            if (isClickable) {
                onClick()
                isClickable = false
                clicked = true
            }
        },
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = saveButtonColor,
            disabledContainerColor = disabledButton,
            disabledContentColor = Color.White
        )
    ) {
        Text(text, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
    }
}
