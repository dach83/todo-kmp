
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import di.appModule
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        val s: String = koinInject()
        Text(text = s)
    }
}
