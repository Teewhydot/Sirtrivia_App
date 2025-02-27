package com.sirteefyapps.sirtrivia.presentation.widgets

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sirteefyapps.sirtrivia.models.QuestionsItem
import com.sirteefyapps.sirtrivia.ui.theme.Typography
import com.sirteefyapps.sirtrivia.utils.AppColors

@Composable
fun QuestionText(
    question: QuestionsItem,
    animate: Boolean = false,
) {
    var startAnimation by remember { mutableStateOf(false) }
    val horizontalOffset by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else 1000.dp, // Slide in from 1000.dp (right) to 0.dp (final position)
        animationSpec = tween(durationMillis = 500) // 0.5-second animation
    )

    LaunchedEffect(animate) {
        if (animate) {
            startAnimation = true
        }
    }

    Surface(
        modifier = Modifier
            .offset(x = horizontalOffset),
        color = AppColors.darkPurple
    ) {
        Text(
            text = question.question,
            style = Typography.displayMedium.copy(
                fontSize = 15.sp,
                color = AppColors.white
            )
        )
    }
}
