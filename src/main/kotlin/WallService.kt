import exceptions.CommentNotFoundException
import exceptions.PostNotFoundException
import exceptions.UnknownReasonException

object WallService { //Объект WallService, который хранит посты в массиве.
    private var posts = emptyArray<Post>() //Переменная с пустым массивом для хранения постов
    private var nextId = 0 //Для нумерации постов в массиве
    private var comments = emptyArray<Comment>()
    private var reports = emptyArray<ReportComment>()

    fun add(post: Post): Post { //Функция для добавления постов в массив posts
        posts += post.copy(id = ++nextId) //Добавляет в массив posts новый пост, присваивает ему id
        return posts.last() //Возвращает последний элемент массива posts
    }
    fun update(postUp: Post): Boolean {
        var result = false
        for ((index, post) in posts.withIndex()) { //Перебор постов по индексу (for each)
            if (post.id == postUp.id) { //Сравнение id поста с id нового, переданного в функцию поста
                posts[index] = postUp.copy() //Результат копии нового поста записывается в массив
                result = true
            }
        }
        return result
    }
    fun printAll() {
        for (post in posts) {
            println(post)
        }
        println()
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        for ((index, p) in posts.withIndex()) {
            if (p.id == postId) {
                comments += comment.copy(text = "New comment $index")
                return comment
            }
        }
        throw PostNotFoundException("No post with $postId")
    }

    fun createReport(postId: Int, commentId: Int, report: ReportComment) : ReportComment {
        if (report.reason < 0 || report.reason > 6) throw UnknownReasonException("Случилось что-то непонятное")
        for (post in posts) {
            if (post.id == postId) {
                for (comment in comments) {
                    if (comment.id == commentId) {
                        reports += report
                        return reports.last()
                    }
                }
                throw CommentNotFoundException("No comment with $commentId")
            }
        }
        throw PostNotFoundException("No post with $postId")
    }


    fun clear() {
        posts = emptyArray() //Очищает массив
        nextId = 0 //Сбрасывает счётчик
    }
}