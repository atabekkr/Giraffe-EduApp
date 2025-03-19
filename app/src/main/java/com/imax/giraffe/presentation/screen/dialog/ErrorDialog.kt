package com.imax.giraffe.presentation.screen.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imax.giraffe.R
import com.imax.giraffe.presentation.ui.components.StandardButtonWithoutPadding

@Composable
fun ErrorDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = null,
        containerColor = Color.Transparent,
        text = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f)) // Отступ сверху

                Card(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(32.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_wrong),
                            contentDescription = "Error Icon",
                            modifier = Modifier.size(112.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f)) // Отступ снизу перед кнопкой

                StandardButtonWithoutPadding(text = "Try Again") {
                    onDismiss()
                }

                Spacer(modifier = Modifier.height(16.dp)) // Дополнительный отступ снизу
            }

        },
        confirmButton = {},
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))

    )
}

@Preview
@Composable
fun DialogPreview() {
    ErrorDialog {  }
}
