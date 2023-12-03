package data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.github.dach83.todo.Database
import com.github.dach83.todo.Todo
import domain.DispatchersProvider
import domain.TodoRepository
import domain.model.TodoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SqlDelightTodoRepository(
    database: Database,
    private val dispatchersProvider: DispatchersProvider
) : TodoRepository {

    private val todoQueries = database.todoQueries

    override suspend fun getTodos(): Flow<List<TodoEntity>> = todoQueries
        .selectAll()
        .asFlow()
        .mapToList(dispatchersProvider.io)
        .map { list ->
            list.map { it.toTodoEntity() }
        }

    override suspend fun addTodo(todo: TodoEntity) {
        todoQueries.insert(todo.toTodo())
    }

    override suspend fun updateTodo(todo: TodoEntity) {
        todoQueries.update(todo.toTodo())
    }

    override suspend fun deleteTodo(todo: TodoEntity) {
        todoQueries.delete(todo.id)
    }
}

private fun Todo.toTodoEntity() = TodoEntity(
    id = id,
    title = title,
    subtitle = subtitle,
    done = done ?: false
)

private fun TodoEntity.toTodo() = Todo(
    id = id,
    title = title,
    subtitle = subtitle,
    done = done
)
