package chat

object ChatService {
    private var chats = mutableMapOf<Int, Chat>()
    fun clear() {
        chats = mutableMapOf()
    }
    fun addMessage(recepient: Int, message: Message) {
        chats.getOrPut(recepient) { Chat() }.messages.add(message)
    }

    fun printChats() {
        println(chats)
    }

    fun unreadChatsCount() = chats.values.count { chat -> chat.messages.any { !it.isUnread } }

    fun getMessages(recepient: Int, messagesCount: Int): List<Message> {
        return chats[recepient]?.messages.orEmpty().takeLast(messagesCount).onEach { it.isUnread = true }
    }

    fun deleteMessages(recepient: Int, messageId: Int) {
        chats[recepient]?.messages.orEmpty().take(messageId).onEach { it.isDeleted = true }
        if (chats[recepient]?.messages.orEmpty().count { !it.isDeleted } == 0) {
            chats.remove(recepient)
        }
    }

    fun deleteChat(recepient: Int) {
        chats.remove(recepient)
    }

    fun getChats() = chats.mapValues { it.value.messages[0] }
}