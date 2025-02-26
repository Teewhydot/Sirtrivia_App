package com.sirteefyapps.sirtrivia.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.sirteefyapps.sirtrivia.presentation.vm.QuestionsViewModel
import com.sirteefyapps.sirtrivia.utils.AppColors

@Composable
fun QuestionsTracker(viewModel: QuestionsViewModel) {
    val total = viewModel.questionList.value.data?.size ?: 0
    val count = viewModel.currentQuestionIndex.intValue + 1
    val score = viewModel.currentScore.intValue
  Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
  ) {
      Text(
          text = buildAnnotatedString {
              withStyle(
                  style = ParagraphStyle(
                      textIndent = TextIndent.None,
                  )
              ){
                  withStyle(
                      SpanStyle(
                          fontSize = 27.sp,
                          color = AppColors.white,
                          fontWeight = FontWeight.Bold                )
                  ) {
                      append("Question $count")
                      withStyle(
                          SpanStyle(
                              fontSize = 14.sp,
                              color = AppColors.lightGray,
                              fontWeight = FontWeight.Normal,
                          )
                      ) {
                          append("/$total")
                      }

                  }            }
          }
      )
      Text(
          text = "Score $score",
          style = TextStyle(
              fontSize = 27.sp,
              color = AppColors.white,
              fontWeight = FontWeight.Bold,
          )
      )
  }
}
