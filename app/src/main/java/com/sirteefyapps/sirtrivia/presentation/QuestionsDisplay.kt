package com.sirteefyapps.sirtrivia.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.sirteefyapps.sirtrivia.ui.theme.Typography
import com.sirteefyapps.sirtrivia.utils.AppColors

@Composable
fun QuestionsDisplay(){
    Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight(
        fraction = 0.4f
    ), color = AppColors.darkPurple) {
        // Display questions here
        Text(
            text = "What is the capital of Nigeria?",
            style = Typography.displayMedium.copy(
                fontSize = 15.sp,
                color = AppColors.white
            )
        )
    }
}
