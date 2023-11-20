package presentation.todo

import domain.model.TodoEntity

data class TodoUiState(
    val todos: List<TodoEntity>
) {
    companion object {
        val DEFAULT = TodoUiState(
            todos = emptyList()
        )
    }
}
