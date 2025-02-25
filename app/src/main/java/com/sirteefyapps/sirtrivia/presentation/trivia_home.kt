package com.sirteefyapps.sirtrivia.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sirteefyapps.sirtrivia.presentation.vm.QuestionsViewModel
import com.sirteefyapps.sirtrivia.presentation.widgets.DottedDivider
import com.sirteefyapps.sirtrivia.presentation.widgets.TriviaButton
import com.sirteefyapps.sirtrivia.presentation.widgets.TriviaNavigateButton
import com.sirteefyapps.sirtrivia.ui.theme.Typography
import com.sirteefyapps.sirtrivia.utils.AppColors

@Preview

@Composable
fun TriviaHome(modifier: Modifier = Modifier,viewModel: QuestionsViewModel = viewModel()) {
val questions  = viewModel.questionList
    val currentQuestionIndex = viewModel.currentQuestionIndex.intValue

    Surface(modifier = Modifier.fillMaxSize(), color = AppColors.darkPurple) {
        Column(modifier = Modifier.padding(20.dp)) {
            questions.value.data?.let {
                QuestionsTracker(
                    total = it.size,
                    count = currentQuestionIndex + 1
                )
            }
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            DottedDivider(pathEffect = PathEffect.dashPathEffect(
                phase = 10f,
                intervals = floatArrayOf(10f, 10f)
            ))
            QuestionAndChoices(
                viewModel = viewModel
            )
        }
}
}
@Composable
fun QuestionAndChoices(viewModel: QuestionsViewModel = viewModel()) {
    val questions = viewModel.questionList
    val currentQuestionIndex = viewModel.currentQuestionIndex.intValue
    val question = questions.value.data?.getOrNull(currentQuestionIndex)
    var isButtonTapped by remember { mutableStateOf(false) }

    // State to track the color of each button by its index
    val buttonColors = remember { mutableStateMapOf<Int, Color>() }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.4f),
                color = AppColors.darkPurple
            ) {
                // Display questions here
                if (question != null) {
                    Text(
                        text = question.question,
                        style = Typography.displayMedium.copy(
                            fontSize = 15.sp,
                            color = AppColors.white
                        )
                    )
                }
            }

            if (question != null) {
                for (i in 1..question.choices.size) {
                    // Display options here
                    TriviaButton(
                        buttonText = question.choices[i - 1],
                        onTap = {
                            // Update the button color based on the answer
                            val isCorrect = question.choices[i - 1] == question.answer
                            buttonColors[i - 1] = if (isCorrect) Color.Green else Color.Red
                            isButtonTapped = true
                        },
                        index = i - 1,
                        buttonColor = buttonColors[i - 1] ?: AppColors.brown,
                        answer = question.answer,
                    )
                }
            }
        }

        // Next button (conditionally displayed)
        if (isButtonTapped) {
            TriviaNavigateButton(
                buttonText = "Next",
                onTap = {
                    buttonColors.clear()

                    viewModel.moveToNextQuestion()
                    isButtonTapped = false
                    buttonColors.clear() // Reset button states for the next question
                },
                buttonColor = AppColors.lightBlue,
            )
        }
    }
}
