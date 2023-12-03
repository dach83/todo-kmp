package domain.model

data class TodoSection(
    val header: String?,
    val todos: List<TodoEntity>
) {
    val todosWithRoles = todos.associateWith { todo ->
        when {
            todos.size == 1 -> TodoRole.SINGLE
            todos.indexOf(todo) == 0 -> TodoRole.TOP
            todos.indexOf(todo) == todos.size - 1 -> TodoRole.BOTTOM
            else -> TodoRole.MIDDLE
        }
    }

    companion object {
        val EMPTY = TodoSection(
            header = null,
            todos = emptyList()
        )
    }
}
