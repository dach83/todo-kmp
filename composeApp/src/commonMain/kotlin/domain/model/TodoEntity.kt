package domain.model

data class TodoEntity(
    val id: Long = 0,
    val title: String,
    val subtitle: String,
    val done: Boolean = false
)
