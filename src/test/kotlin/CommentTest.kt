import WallService.createComment
import exceptions.CommentNotFoundException
import exceptions.PostNotFoundException
import exceptions.UnknownReasonException
import org.junit.Test

import org.junit.Assert.*
//Задание к занятию «3.1. Исключения»
class CommentTest {

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val postId = 999
        val comment1 = Comment()
        var result = createComment(postId, comment1)
        assertEquals(true, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun reportPostNotFoundException() {
        val post = WallService.add(Post(id = 0, text = "пост", copyright = "text"))
        val falsePostId = post.id + 1 //Создаем неправильный id поста
        val comment = Comment(0, falsePostId,0, "some thread", 0, 1)
        WallService.createComment(falsePostId, comment)
    }

    @Test(expected = CommentNotFoundException::class)
    fun reportCommentNotFoundException() {
        val post = WallService.add(Post(id = 0, text = "пост", copyright = "text"))
        val comment = Comment(0, post.id, 0, "some thread", 0, 1)
        WallService.createComment(post.id, comment)
        val falseCommentId = comment.id + 1 // создаем неправильный id комментария
        val report = ReportComment(0, falseCommentId, 1)
        WallService.createReport(post.id, falseCommentId, report)
    }

    @Test(expected = UnknownReasonException::class)
    fun reportShouldThrowUnknownReasonException() {
        val post = WallService.add(Post(id = 0, text = "пост", copyright = "text"))
        val comment = Comment(0, post.id,0, "some thread", 0, 1)
        WallService.createComment(post.id, comment)
        val report = ReportComment(0, comment.id, -1)
        WallService.createReport(post.id, comment.id, report)
    }
}