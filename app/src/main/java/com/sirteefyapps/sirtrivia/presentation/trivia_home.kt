package com.sirteefyapps.sirtrivia.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sirteefyapps.sirtrivia.presentation.vm.QuestionsViewModel
import com.sirteefyapps.sirtrivia.presentation.widgets.DottedDivider
import com.sirteefyapps.sirtrivia.presentation.widgets.QuestionText
import com.sirteefyapps.sirtrivia.presentation.widgets.TriviaButton
import com.sirteefyapps.sirtrivia.presentation.widgets.TriviaNavigateButton
import com.sirteefyapps.sirtrivia.utils.AppColors
import com.sirteefyapps.sirtrivia.utils.SharedPreferencesHelper

@Preview

@Composable
fun TriviaHome(modifier: Modifier = Modifier,viewModel: QuestionsViewModel = viewModel()) {
    val context = LocalContext.current
    val sharedPreferencesHelper = remember { SharedPreferencesHelper(context) }
    sharedPreferencesHelper.getUserProgress("userProgress")?.let {
        viewModel.currentQuestionIndex.intValue = (it["currentQuestionIndex"] as? Number)?.toInt() ?: 0
        viewModel.currentScore.intValue = (it["currentScore"] as? Number)?.toInt() ?: 0
    }
val questions  = viewModel.questionList
    Surface(modifier = Modifier.fillMaxSize(), color = AppColors.darkPurple) {
        Column(modifier = Modifier.padding(20.dp).windowInsetsPadding(WindowInsets.safeContent)) {
            questions.value.data?.let {
                QuestionsTracker(
                  viewModel = viewModel
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

    val context = LocalContext.current
    val sharedPreferencesHelper = remember { SharedPreferencesHelper(context) }
    // State to hold the Map data
    val userMap = mapOf(
        "currentQuestionIndex" to currentQuestionIndex,
        "currentScore" to viewModel.currentScore.intValue
    )
    var shouldButtonAnimate by remember { mutableStateOf(false) } // State to control button animation
    var shouldTextAnimate by remember { mutableStateOf(false) } // State to control text animation

    LaunchedEffect(viewModel.currentQuestionIndex.intValue) {
        shouldButtonAnimate = true
        shouldTextAnimate = true
        }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.3f),
                color = AppColors.darkPurple
            ) {
                // Display questions here
                if (question != null) {
                key (viewModel.currentQuestionIndex.intValue){
                    QuestionText(question, shouldTextAnimate)
                }
                }
            }
            if (question != null) {
                for (i in 1..question.choices.size) {
                    val delay = (i - 1) * 200 // 200ms delay between each button
                    // Display options here
                   key(viewModel.currentQuestionIndex.intValue){
                       TriviaButton(
                           buttonText = question.choices[i - 1],
                           delay = delay,
                           animate = shouldButtonAnimate,
                           onTap = {
                               // Update the button color based on the answer
                               val isCorrect = question.choices[i - 1] == question.answer
                               buttonColors[i - 1] = if (isCorrect) Color.Green else Color.Red
                               isButtonTapped = true
                               shouldButtonAnimate = false
                               shouldTextAnimate = true
                               if(isCorrect){
                                   viewModel.currentScore.intValue += 1
                                   sharedPreferencesHelper.saveUserProgress("userProgress", userMap)
                               }
                           },
                           buttonColor = buttonColors[i - 1] ?: AppColors.lightPurple,
                       )
                   }
                }
            }
        }

        // Next button (conditionally displayed)
        if (isButtonTapped) {
           Row {
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
               TriviaNavigateButton(
                   buttonText = "Reset Quiz",
                   onTap = {
                       buttonColors.clear()
                       viewModel.resetQuiz()
                       isButtonTapped = false
                       buttonColors.clear() // Reset button states for the next question
                   },
                   buttonColor = AppColors.brown,
               )
           }
        }
    }
}
