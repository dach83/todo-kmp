package di

import data.InMemoryTodoRepository
import domain.TodoRepository
import org.koin.dsl.module
import presentation.todo.TodoViewModel

val appModule = module {

    single<TodoRepository> { InMemoryTodoRepository() }

    viewModelDefinition {
        TodoViewModel(
            dispatchers = get(),
            repository = get()
        )
    }
}
