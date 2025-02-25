package com.sirteefyapps.sirtrivia.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sirteefyapps.sirtrivia.ui.theme.Typography

fun beveledCornersShape(
    cornerSize: Dp = 10.dp, // Adjust the bevel size here
): Shape = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cornerSizePx = with(density) { cornerSize.toPx() }

        return Outline.Generic(
            path = Path().apply {
                // Top-left corner (unchanged)
                moveTo(0f, 0f)
                // Top-right corner (beveled)
                lineTo(size.width - cornerSizePx, 0f)
                lineTo(size.width, cornerSizePx)
                // Bottom-right corner (unchanged)
                lineTo(size.width, size.height)
                // Bottom-left corner (beveled)
                lineTo(cornerSizePx, size.height)
                lineTo(0f, size.height - cornerSizePx)
                close()
            }
        )
    }
}


@Composable
fun TriviaButton(buttonText: String,
                 index: Int,
                 onTap: () -> Unit,
                 buttonColor: Color,
                 answer:String,) {
    Surface(modifier = Modifier.height(90.dp).fillMaxWidth(
    ).padding(20.dp).clickable {
        onTap()
    }, shape = beveledCornersShape(
        cornerSize = 10.dp
    ), color = buttonColor) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
              text = buttonText,
              style = Typography.labelSmall.copy(
                  color = Color.White,
                  fontSize = 15.sp
              ),
              textAlign = TextAlign.Center,
          )
      }
    }
}


@Composable
fun TriviaNavigateButton(buttonText: String, onTap: () -> Unit,buttonColor: Color) {

    Surface(modifier = Modifier.height(90.dp).width(190.dp).padding(20.dp).clickable {
        onTap()
    }, shape = beveledCornersShape(
        cornerSize = 10.dp
    ), color = buttonColor) {


        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = buttonText,
                style = Typography.labelSmall.copy(
                    color = Color.White,
                    fontSize = 15.sp
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}
