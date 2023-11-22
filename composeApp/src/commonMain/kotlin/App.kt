import androidx.compose.runtime.Composable
import di.appModule
import di.platformModule
import org.koin.compose.KoinApplication
import presentation.todo.TodoScreen
import theme.TodoTheme

@Composable
fun App() {
    KoinApplication(
        application = {
            modules(appModule, platformModule)
        }
    ) {
        TodoTheme {
            TodoScreen()
        }
    }
}
