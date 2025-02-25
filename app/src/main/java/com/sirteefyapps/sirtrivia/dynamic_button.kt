
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sirteefyapps.sirtrivia.utils.AppColors

@Composable
fun DynamicButtons(options: List<String>) {
    // State to track the color of each button by its index
    val buttonColors = remember { mutableStateMapOf<Int, Color>() }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        options.forEachIndexed { index, option ->
            Button(
                onClick = {
                    // Toggle or set the color based on your logic
                    val currentColor = buttonColors[index]
                    val newColor =
                        if(option == "Option 1") Color.Green else Color.Red
                    buttonColors[index] = newColor
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .background(
                        color = buttonColors[index] ?: AppColors.brown, // Default color is brown
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text(text = option)
            }
            Button(
                onClick = {
                    // Clear the state of all buttons
                    buttonColors.clear()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(8.dp))
            ) {
                Text(text = "Reset", color = Color.White)
            }
        }
    }
}

// Example usage
@Composable
fun ExampleScreen() {
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4")
    DynamicButtons(options)
}
