package di

import data.SqlDelightTodoRepository
import domain.TodoRepository
import org.koin.dsl.module
import presentation.todo.TodoViewModel

val commonModule = module {

    single<TodoRepository> {
        SqlDelightTodoRepository(
            database = get(),
            dispatchersProvider = get()
        )
//        InMemoryTodoRepository()
    }

    viewModelDefinition {
        TodoViewModel(
            dispatchers = get(),
            repository = get()
        )
    }
}
