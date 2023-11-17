package di

import data.InMemoryRepository
import domain.Repository
import org.koin.dsl.module

val appModule = module {

    single<String> { "Hello koin" }

    single<Repository> { InMemoryRepository() }
}
