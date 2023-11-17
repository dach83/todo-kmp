package data

import domain.Repository
import domain.model.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class InMemoryRepository : Repository {

    private var lastId = 0
    private val todos = MutableStateFlow<List<TodoItem>>(emptyList())

    override suspend fun getTodos(): Flow<List<TodoItem>> {
        return todos
    }

    override suspend fun addTodo(todo: TodoItem) {
        lastId++
        todos.update {
            it.plus(todo.copy(id = lastId))
        }
    }

    override suspend fun updateTodo(todo: TodoItem) {
        todos.update {
            it.map { item ->
                if (item.id == todo.id) todo else item
            }
        }
    }

    override suspend fun deleteTodo(todo: TodoItem) {
        todos.update {
            it.filter { item ->
                item.id != todo.id
            }
        }
    }
}
