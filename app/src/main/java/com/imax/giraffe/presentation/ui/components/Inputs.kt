package com.imax.giraffe.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imax.giraffe.R
import com.imax.giraffe.presentation.ui.theme.gray
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.mainTypography
import com.imax.giraffe.presentation.ui.theme.primaryColor

@Composable
fun MyOutlinedTextField(inputText: (String) -> Unit) {
    var input by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = gray,
            unfocusedBorderColor = gray,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedTextColor = mainTypography,
            unfocusedLabelColor = gray,
        ),
        value = input,
        onValueChange = {
            input = it
            inputText(it)
        },
        textStyle = TextStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp),
        placeholder = {
            Text(
                stringResource(R.string.login_hint_text),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = grayTypography
                )
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun WritingTestInput(
    text: String,
    onTextChange: (String) -> Unit
) {
    val charLimit = 150

    Card(
        modifier = Modifier
            .width(342.dp)
            .height(142.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp, end = 15.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            OutlinedButton(
                onClick = { onTextChange("") },
                modifier = Modifier.size(24.dp),
                shape = CircleShape,
                border = BorderStroke(width = 0.dp, primaryColor),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = primaryColor)
            ) {
                Icon(
                    Icons.Default.Close,
                    modifier = Modifier.padding(4.dp),
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    if (it.length <= charLimit) {
                        onTextChange(it)
                    }
                },
                placeholder = { Text("Write something...", fontSize = 20.sp) },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5,
                textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent
                )
            )
        }
    }
}
