package presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

actual abstract class BaseViewModel {

    protected actual val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main
    )

    fun onCleared() {
        scope.cancel()
    }
}
