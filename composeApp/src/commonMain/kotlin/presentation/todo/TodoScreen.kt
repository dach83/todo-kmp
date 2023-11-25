package presentation.todo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@Composable
fun TodoScreen(
    viewModel: TodoViewModel = koinInject()
) {
    // val state = viewModel.uiState
    Scaffold(
        contentColor = MaterialTheme.colors.secondary,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                contentColor = Color.White,
                backgroundColor = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        },
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .systemBarsPadding()
        ) {
            Text(
                text = "test",
                color = Color.White
            )
        }
    }
}
