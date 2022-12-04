import chat.ChatService
import chat.Message
import junit.framework.TestCase.*
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class ChatServiceTest{
    @Before
    fun clearBeforeTest() {
        ChatService.clear()
    }
    @Test
    fun addMessageTrue() {
        val x = ChatService.getChats()
        ChatService.addMessage(1, Message(messageId = 0, senderId = 1, text = "TEST"))
        val y = ChatService.getChats()
        val result = x.equals(y)
        assertFalse(result)
    }
    @Test
    fun getChatsTrue(){
        val x = ChatService.getChats()
        ChatService.addMessage(1, Message(messageId = 0, senderId = 1, text = "ABC", false, true))
        ChatService.addMessage(1, Message(messageId = 1, senderId = 2, text = "SECOND", false, true))
        ChatService.addMessage(2, Message(messageId = 2, senderId = 3, text = "asd", true, false))
        ChatService.addMessage(3, Message(messageId = 3, senderId = 4, text = "ALPHABET", false, false))
        ChatService.addMessage(3, Message(messageId = 4, senderId = 5, text = "ok, Google", false, false))
        val y = ChatService.getChats()
        val result = x.equals(y)
        assertFalse(result)
    }
    @Test(expected = AssertionError::class)
    fun getChatsThrow(){
        ChatService.addMessage(1, Message(messageId = 0, senderId = 1, text = "ABC", false, true))
        ChatService.addMessage(1, Message(messageId = 1, senderId = 2, text = "SECOND", false, true))
        ChatService.addMessage(3, Message(messageId = 2, senderId = 3, text = "ALPHABET", false, false))
        ChatService.addMessage(3, Message(messageId = 3, senderId = 4, text = "ok, Google", false, false))
        assertThrows(AssertionError::class.java){
            ChatService.getChats()
        }
    }
    @Test
    fun getMessagesTrue(){
        val x = ChatService.getChats()
        ChatService.addMessage(1, Message(messageId = 0, senderId = 1, text = "ABC", false, true))
        ChatService.addMessage(1, Message(messageId = 1, senderId = 2, text = "ALPHABET", false, true))
        ChatService.addMessage(3, Message(messageId = 2, senderId = 3, text = "ok, Google", false, false))
        ChatService.getMessages(1, 1)
        val y = ChatService.getChats()
        val result = x.equals(y)
        assertFalse(result)
    }
    @Test
    fun deleteMessagesTrue(){
        ChatService.addMessage(1, Message(messageId = 0, senderId = 1, text = "ABC", false, true))
        ChatService.addMessage(3, Message(messageId = 1, senderId = 1, text = "ok, Google", false, false))
        val x = ChatService.getChats()
        ChatService.deleteMessages(1, 1)
        val y = ChatService.getChats()
        val result = x.equals(y)
        assertFalse(result)
    }
    @Test
    fun deleteChatTrue(){
        ChatService.addMessage(1, Message(messageId = 0, senderId = 1, text = "ABC", false, true))
        ChatService.addMessage(3, Message(messageId = 1, senderId = 2, text = "ALPHABET", false, false))
        val x = ChatService.getChats()
        ChatService.deleteChat(1)
        val y = ChatService.getChats()
        val result = x.equals(y)
        assertFalse(result)
    }
    @Test
    fun unreadChatsCountTrue() {
        ChatService.addMessage(1, Message(messageId = 0, senderId = 1, text = "ABC", true, true))
        ChatService.addMessage(3, Message(messageId = 1, senderId = 2, text = "ok, Google", false, false))
        val x = ChatService.unreadChatsCount()
        assertEquals(x, 1)
    }
}