package di

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.github.dach83.todo.Database
import domain.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {

    single<DispatchersProvider> {
        object : DispatchersProvider {
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
        }
    }

    single {
        val driver = AndroidSqliteDriver(
            schema = Database.Schema,
            context = androidContext(),
            name = "todo.db"
        )
        Database(driver)
    }
}
