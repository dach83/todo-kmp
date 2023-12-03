package presentation.todo

import domain.model.TodoSection

data class TodoUiState(
    val notTodosYet: Boolean,
    val doneSection: TodoSection,
    val undoneSection: TodoSection
) {
    companion object {
        val DEFAULT = TodoUiState(
            notTodosYet = true,
            doneSection = TodoSection.EMPTY,
            undoneSection = TodoSection.EMPTY
        )
    }
}
