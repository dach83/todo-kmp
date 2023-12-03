package presentation.todo.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.koinInject
import presentation.todo.TodoViewModel

@Composable
fun TodoScreen(
    viewModel: TodoViewModel = koinInject()
) {
    val state by viewModel.uiState.collectAsState()

    var dialogOpen by remember { mutableStateOf(false) }
    if (dialogOpen) {
        AddTodoDialog(
            addTodo = viewModel::addTodo,
            onDismissRequest = { dialogOpen = false }
        )
    }

    Scaffold(
        contentColor = MaterialTheme.colors.secondary,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { dialogOpen = true },
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
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .systemBarsPadding(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = state.notTodosYet,
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                Text(
                    text = "No Todos Yet!",
                    color = Color.White,
                    fontSize = 22.sp
                )
            }

            AnimatedVisibility(
                visible = !state.notTodosYet,
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 8.dp,
                            bottom = paddingValues.calculateBottomPadding() + 8.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    todoSection(
                        section = state.undoneSection,
                        onClick = viewModel::clickTodo,
                        onDelete = viewModel::deleteTodo
                    )
                    todoSection(
                        section = state.doneSection,
                        onClick = viewModel::clickTodo,
                        onDelete = viewModel::deleteTodo
                    )
                }
            }
        }
    }
}
