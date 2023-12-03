package di

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.github.dach83.todo.Database
import domain.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val iosModule = module {

    single<DispatchersProvider> {
        object : DispatchersProvider {
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val io: CoroutineDispatcher
                get() = Dispatchers.Default
        }
    }

    single {
        val driver = NativeSqliteDriver(Database.Schema, "test.db")
        Database(driver)
    }
}
