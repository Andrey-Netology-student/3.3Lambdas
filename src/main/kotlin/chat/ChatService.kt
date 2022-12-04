package ru.netology

import chat.Chat
import chat.Message

object ChatService {
    private var chats = mutableMapOf<Int, Chat>()
    fun clear() {
        chats = mutableMapOf()
    }

    fun addMessage(recepient: Int, message: Message) =
        chats.getOrPut(recepient) { Chat() }.messages.add(message)


    fun printChats() {
        println(chats)
    }

    fun unreadChatsCount() = chats.values.asSequence().count { chat -> chat.messages.any { !it.isUnread } }

    fun getMessages(recepient: Int, messagesCount: Int): List<Message> =
        chats
            .filter { it.key == recepient }
            .values
            .asSequence()
            .map { it.messages }
            .take(messagesCount)
            .ifEmpty { throw ChatNotFoundError() }
            .last()
            .onEach { it.isUnread = true }

    fun deleteMessages(recepient: Int, messageId: Int) {
        chats
            .filter { it.key == recepient }
            .values
            .asSequence()
            .map { it.messages }
            .take(messageId)
            .ifEmpty { throw MessageNotFoundError() }
            .last()
            .onEach { it.isDeleted = true }
            .count { !it.isDeleted }
            .takeIf { it == 0 }
            ?: run {
                chats.remove(recepient)
            }
    }

    fun deleteChat(recepient: Int) {
        chats.remove(recepient)
    }

    fun getChats() = chats
        .values
        .asSequence()
        .map { it.messages[0] }

    class ChatNotFoundError : Exception("Чат не найден!")
    class MessageNotFoundError : Exception("Сообщение не найдено!")

}