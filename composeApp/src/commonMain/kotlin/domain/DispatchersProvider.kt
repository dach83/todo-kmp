package domain

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}
