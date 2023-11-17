package domain

import domain.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getTodos(): Flow<List<TodoItem>>
    suspend fun addTodo(todo: TodoItem)
    suspend fun updateTodo(todo: TodoItem)
    suspend fun deleteTodo(todo: TodoItem)
}
