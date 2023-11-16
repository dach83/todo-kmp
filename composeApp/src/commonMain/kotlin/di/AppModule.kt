package di

import org.koin.dsl.module

val appModule = module {

    single<String> { "Hello koin" }
}
