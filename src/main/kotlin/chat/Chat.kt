package chat

data class Chat(
    val chatId: Int = 0, //Идентификатор чата
    val userId1: Int = 1, //Идентификатор пользователя
    val userId2: Int = 2,
    var isDeleted: Boolean = false, //Удалён чат или нет
    val messages: MutableList<Message> = mutableListOf() //Изменяемый список с сообщениями
)