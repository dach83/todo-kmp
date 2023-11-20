package domain.model

import kotlinx.datetime.Clock

data class TodoEntity(
    val id: Int,
    val title: String,
    val subtitle: String,
    val done: Boolean,
    val added: Long = Clock.System.now().toEpochMilliseconds()
)

// val TodoItem.addedDate: String
//    get() {
//        val dt = Instant
//            .fromEpochMilliseconds(added)
//            .toLocalDateTime(TimeZone.currentSystemDefault())
//        return dt.toString()
//    }
