package theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val darkColorScheme = darkColors(
    primary = Dark500,
    secondary = Dark200
)

@Composable
fun TodoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(colors = darkColorScheme) {
        content()
    }
}
