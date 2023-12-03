import androidx.compose.runtime.Composable
import org.koin.compose.KoinContext
import presentation.todo.compose.TodoScreen
import theme.TodoTheme

@Composable
fun App() {
    KoinContext {
        TodoTheme {
            TodoScreen()
        }
    }
}
