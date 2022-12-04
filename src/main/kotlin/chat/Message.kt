package chat

data class Message(
    val messageId: Int, //Идентификатор сообщения
    val senderId: Int, //Идентификатор отправителя
    val text: String,
    var isDeleted: Boolean = false, //Удалено сообщение или нет
    var isUnread: Boolean = true, //Прочитано сообщение или нет
)