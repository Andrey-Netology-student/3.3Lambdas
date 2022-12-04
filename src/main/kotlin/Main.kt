import chat.Message
import ru.netology.ChatService

fun main(args: Array<String>) {
    ChatService.addMessage(1, Message("hihihi", false, true))
    ChatService.addMessage(1, Message("hihihi", false, true))
    ChatService.addMessage(2, Message("asd", true, false))
    ChatService.addMessage(3, Message("aaasd", false, false))
    ChatService.addMessage(3, Message("2342aaasd", false, false))
    ChatService.addMessage(4, Message("aaasd", true, false))
    //  ChatService.printChats()
    println(ChatService.getChats())
}
//gradle test jacocoTestReport