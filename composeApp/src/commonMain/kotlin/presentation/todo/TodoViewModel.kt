package presentation.todo

import domain.DispatchersProvider
import domain.TodoRepository
import domain.model.TodoEntity
import domain.model.TodoSection
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

    private fun getTodos() {
        scope.launch(dispatchers.io) {
            repository.getTodos().collect { todos ->
                val (doneTodos, undoneTodos) = todos.partition { it.done }
                val doneSection = TodoSection(
                    header = "Done",
                    todos = doneTodos
                )
                val undoneSection = TodoSection(
                    header = null,
                    todos = undoneTodos
                )
                _uiState.update {
                    it.copy(
                        notTodosYet = todos.isEmpty(),
                        doneSection = doneSection,
                        undoneSection = undoneSection
                    )
                }
            }
        }
    }

    fun addTodo(todo: TodoEntity) {
        scope.launch(dispatchers.io) {
            repository.addTodo(todo)
        }
    }

    fun deleteTodo(todo: TodoEntity) {
        scope.launch(dispatchers.io) {
            repository.deleteTodo(todo)
        }
    }

    private fun updateTodo(todo: TodoEntity) {
        scope.launch(dispatchers.io) {
            repository.updateTodo(todo)
        }
    }

    fun clickTodo(todo: TodoEntity) {
        updateTodo(todo.copy(done = !todo.done))
    }
}
