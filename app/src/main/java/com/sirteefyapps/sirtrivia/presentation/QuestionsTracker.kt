package com.sirteefyapps.sirtrivia.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.sirteefyapps.sirtrivia.utils.AppColors

@Composable
fun QuestionsTracker(count: Int = 10, total: Int= 38489){
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
}
