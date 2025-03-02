package com.imax.giraffe.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imax.giraffe.presentation.ui.theme.primaryColor

@Composable
fun StandardButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .height(56.dp),
        onClick = {
            onClick.invoke()
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = primaryColor
        )
    ) {
        Text(text, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
    }
}

@Composable
fun StandardButtonWithoutPadding(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        onClick = {
            onClick.invoke()
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = primaryColor
        )
    ) {
        Text(text, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
    }
}