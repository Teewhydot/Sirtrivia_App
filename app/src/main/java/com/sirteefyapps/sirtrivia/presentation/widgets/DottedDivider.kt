package com.sirteefyapps.sirtrivia.presentation.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import com.sirteefyapps.sirtrivia.utils.AppColors

@Composable
fun DottedDivider(
    pathEffect: PathEffect,
){
    Canvas(modifier = Modifier.height(2.dp
    ).fillMaxWidth()) {
        drawLine(
            color = AppColors.white,
            pathEffect = pathEffect,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
        )
    }
}
