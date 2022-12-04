import chat.Message
import junit.framework.TestCase.*
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import ru.netology.ChatService

class ChatServiceTest{
    @Before
    fun clearBeforeTest() {
        ChatService.clear()
    }
    @Test
    fun addMessageTrue() {
        val x = ChatService.getChats()
        ChatService.addMessage(1, Message("TEST", false, true))
        val y = ChatService.getChats()
        val result = x.equals(y)
        assertFalse(result)
    }
    @Test
    fun getChatsTrue(){
        val x = ChatService.getChats()
        ChatService.addMessage(1, Message("ABC", false, true))
        ChatService.addMessage(1, Message("SECOND", false, true))
        ChatService.addMessage(2, Message("asd", true, false))
        ChatService.addMessage(3, Message("ALPHABET", false, false))
        ChatService.addMessage(3, Message("ok, Google", false, false))
        val y = ChatService.getChats()
        val result = x.equals(y)
        assertFalse(result)
    }
    @Test(expected = AssertionError::class)
    fun getChatsThrow(){
        ChatService.addMessage(1, Message("ABC", false, true))
        ChatService.addMessage(1, Message("SECOND", false, true))
        ChatService.addMessage(3, Message("ALPHABET", false, false))
        ChatService.addMessage(3, Message("ok, Google", false, false))
        assertThrows(AssertionError::class.java){
            ChatService.getChats()
        }
    }
    @Test
    fun getMessagesTrue(){
        val x = ChatService.getChats()
        ChatService.addMessage(1, Message("ABC", false, true))
        ChatService.addMessage(1, Message("ALPHABET", false, true))
        ChatService.addMessage(3, Message("ok, Google", false, false))
        ChatService.getMessages(1, 1)
        val y = ChatService.getChats()
        val result = x.equals(y)
        assertFalse(result)
    }
    @Test
    fun deleteMessagesTrue(){
        ChatService.addMessage(1, Message("ABC", false, true))
        ChatService.addMessage(3, Message("ok, Google", false, false))
        val x = ChatService.getChats()
        ChatService.deleteMessages(1, 1)
        val y = ChatService.getChats()
        val result = x.equals(y)
        assertFalse(result)
    }
    @Test
    fun deleteChatTrue(){
        ChatService.addMessage(1, Message("ABC", false, true))
        ChatService.addMessage(3, Message("ALPHABET", false, false))
        val x = ChatService.getChats()
        ChatService.deleteChat(1)
        val y = ChatService.getChats()
        val result = x.equals(y)
        assertFalse(result)
    }
    @Test
    fun unreadChatsCountTrue() {
        ChatService.addMessage(1, Message("ABC", true, true, false))
        ChatService.addMessage(3, Message("ok, Google", false, false))
        val x = ChatService.unreadChatsCount()
        assertEquals(x, 1)
    }
}