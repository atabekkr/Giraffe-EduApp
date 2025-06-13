package com.imax.giraffe.presentation.screen.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imax.giraffe.R
import com.imax.giraffe.presentation.ui.components.StandardButtonForCongratsDialog
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.mainTypography
import com.imax.giraffe.presentation.utils.playCongratsSound

@Composable
fun CongratsDialog(onDismiss: () -> Unit) {

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        context.playCongratsSound()
    }

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
                        .height(500.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(32.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.padding(bottom = 30.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pic_congrats),
                                contentDescription = "Error Icon",
                                modifier = Modifier.size(300.dp)
                            )
                            Text(
                                text = "Congratulations",
                                style = TextStyle(
                                    color = mainTypography,
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                            )
                            Text(
                                text = "You did a great job in the test\n" +
                                        "and earn feed for your pet",
                                modifier = Modifier.padding(top = 6.dp),
                                style = TextStyle(
                                    color = grayTypography,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f)) // Отступ снизу перед кнопкой

                StandardButtonForCongratsDialog(text = "Continue") {
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
fun CongratsDialogPreview() {
    CongratsDialog { }
}
