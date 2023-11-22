package presentation.todo

import domain.DispatchersProvider
import domain.TodoRepository
import domain.model.TodoEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.BaseViewModel

class TodoViewModel(
    private val dispatchers: DispatchersProvider,
    private val repository: TodoRepository
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(TodoUiState.DEFAULT)
    val uiState = _uiState.asStateFlow()

    init {
        getTodos()
    }

    fun getTodos() {
        scope.launch(dispatchers.io) {
            repository.getTodos().collect { todos ->
                _uiState.update {
                    it.copy(todos = todos)
                }
            }
        }
    }

    fun addTodo(todo: TodoEntity) {
        scope.launch(dispatchers.io) {
            repository.addTodo(todo)
        }
    }

    fun updateTodo(todo: TodoEntity) {
        scope.launch(dispatchers.io) {
            repository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: TodoEntity) {
        scope.launch(dispatchers.io) {
            repository.deleteTodo(todo)
        }
    }
}
