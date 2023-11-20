package presentation.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import domain.TodoRepository
import domain.model.TodoEntity
import kotlinx.coroutines.launch
import presentation.BaseViewModel

class TodoViewModel(
    private val repository: TodoRepository
) : BaseViewModel() {

    var uiState by mutableStateOf(TodoUiState.DEFAULT)
        private set

    init {
        getTodos()
    }

    fun getTodos() {
        scope.launch {
            repository.getTodos().collect {
                uiState = uiState.copy(todos = it)
            }
        }
    }

    fun addTodo(todo: TodoEntity) {
        scope.launch {
            repository.addTodo(todo)
        }
    }

    fun updateTodo(todo: TodoEntity) {
        scope.launch {
            repository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: TodoEntity) {
        scope.launch {
            repository.deleteTodo(todo)
        }
    }
}
