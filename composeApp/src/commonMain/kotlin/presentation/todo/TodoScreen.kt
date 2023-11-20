package presentation.todo

import androidx.compose.runtime.Composable
import org.koin.compose.koinInject

@Composable
fun TodoScreen(
    viewModel: TodoViewModel = koinInject()
) {
}
