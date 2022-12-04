data class Comment(
    val id: Int = 0, //Идентификатор записи
    val fromId: Int = 0, //Идентификатор автора записи
    val date: Long = System.currentTimeMillis() / 1000L, //Время публикации записи
    val text: String = "Comment text", //Текст записи
    val replyToUser: Int = 1,
    val replyToComment: Int = 1,
    val ownerId: Int = 0,
    var isDeleted: Boolean = false,
)