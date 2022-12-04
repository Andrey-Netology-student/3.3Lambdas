package chat

data class Message (
    val text: String,
    var read: Boolean,
    var delete: Boolean,
    var isUnread: Boolean = true,
    var isDeleted: Boolean = true
)