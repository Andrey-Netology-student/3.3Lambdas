package note

import Comment
import java.time.LocalDate

data class Note (
    val ownerId: Int,
    val title: String,
    val text: String,
    val privacy: Byte = 0,
    val commentPrivacy: Byte = 0,
    val privacyView: String,
    val privacyComment: String,
    val id: Int = 0,
    val date: LocalDate = LocalDate.now(),
    var isDeleted: Boolean = false,
    var comments: MutableList<Comment> = mutableListOf<Comment>()
    )