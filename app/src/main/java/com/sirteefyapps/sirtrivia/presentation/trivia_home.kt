package com.sirteefyapps.sirtrivia.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sirteefyapps.sirtrivia.presentation.widgets.DottedDivider
import com.sirteefyapps.sirtrivia.utils.AppColors

@Preview

@Composable
fun TriviaHome(modifier: Modifier = Modifier) {
Surface(modifier = Modifier.fillMaxSize(), color = AppColors.darkPurple) {
        Column(modifier = Modifier.padding(20.dp)) {
            QuestionsTracker()
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            DottedDivider(pathEffect = PathEffect.dashPathEffect(
                phase = 10f,
                intervals = floatArrayOf(10f, 10f)
            ))
            QuestionsDisplay()
            OptionsDisplay()
        }
}
}
