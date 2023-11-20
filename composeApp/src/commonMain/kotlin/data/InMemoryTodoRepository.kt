package data

import domain.TodoRepository
import domain.model.TodoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class InMemoryTodoRepository : TodoRepository {

    private var lastId = 0
    private val todos = MutableStateFlow<List<TodoEntity>>(emptyList())

    override suspend fun getTodos(): Flow<List<TodoEntity>> {
        return todos
    }

    override suspend fun addTodo(todo: TodoEntity) {
        lastId++
        todos.update {
            it.plus(todo.copy(id = lastId))
        }
    }

    override suspend fun updateTodo(todo: TodoEntity) {
        todos.update {
            it.map { item ->
                if (item.id == todo.id) todo else item
            }
        }
    }

    override suspend fun deleteTodo(todo: TodoEntity) {
        todos.update {
            it.filter { item ->
                item.id != todo.id
            }
        }
    }
}
