package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imax.giraffe.R
import com.imax.giraffe.presentation.navigation.Screen
import com.imax.giraffe.presentation.screen.viewmodel.MainViewModel
import com.imax.giraffe.presentation.screen.viewmodel.UserViewModel
import com.imax.giraffe.presentation.ui.components.Grade
import com.imax.giraffe.presentation.ui.theme.grayTypography
import com.imax.giraffe.presentation.ui.theme.mainTypography
import com.imax.giraffe.presentation.utils.GradeContent

@Composable
fun ChooseGradeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    onNavigateToScreen: (Screen) -> Unit
) {

    val userName = userViewModel.getUserName()

    LaunchedEffect(viewModel) {
        viewModel.getGrades()
    }
    val grades = viewModel.getGradesState.collectAsState().value
    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.background1),
                contentScale = ContentScale.Crop
            )
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Hi $userName!",
                    style = TextStyle(
                        color = mainTypography,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    text = stringResource(R.string.welcome_to_school_education),
                    modifier = Modifier.padding(top = 6.dp),
                    style = TextStyle(
                        color = grayTypography,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Image(
                painter = painterResource(R.drawable.pic_giraffe),
                modifier = Modifier.size(54.dp),
                contentDescription = "Giraffe"
            )
        }
        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            grades?.let {
                items(it) { grade ->
                    val gradeContent = when (grade.id) {
                        1 -> GradeContent.GRADE1
                        2 -> GradeContent.GRADE2
                        3 -> GradeContent.GRADE3
                        4 -> GradeContent.GRADE4
                        else -> GradeContent.GRADE4
                    }
                    Grade(
                        grade,
                        gradeContent.color,
                        gradeContent.picId
                    ) {
                        userViewModel.setGradeId(grade.id)
                        onNavigateToScreen.invoke(
                            Screen.AfterChooseGrade
                        )
                    }
                }
            }
        }
    }

}

@Composable
@Preview
fun ChooseGradePreview() {
    ChooseGradeScreen { }
}