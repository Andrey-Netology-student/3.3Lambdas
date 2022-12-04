data class Post(
    val id: Int = 0, //Идентификатор записи
    val ownerId: Int = 0, //Идентификатор владельца стены, на которой размещена запись
    val fromId: Int = 0, //Идентификатор автора записи
    val createdBy: Int = 0, //Идентификатор администратора, который опубликовал запись
    val date: Long = System.currentTimeMillis() / 1000L, //Время публикации записи
    val text: String, //Текст записи
    val replyOwnerId: Int = 0, //Идентификатор владельца записи, в ответ на которую была оставлена текущая
    val replyPostId: Int = 0, //Идентификатор записи, в ответ на которую была оставлена текущая
    val friendsOnly: Boolean = true, //Если запись была создана с опцией "только для друзей"
    val comment: Comment = Comment(), //Информация о комментариях к записи, объект с полями
    val copyright: String, //Источник материала
    val likes: Likes = Likes(), //Информация о лайках к записи, объект с полями
    val reposts: Int = 0, //Информация о репостах записи (рассказать друзьям), объект с полями
    val views: Int = 0, //Информация о просмотрах записи. Объект с единственным полем
    val postType: String = "post", //Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest
    val postSource: PostSource? = null, //Информация о способе размещения записи
    val attachments: Array<Attachment> = emptyArray(), //Медиа вложения записи (фотографии, ссылки и т.п.)
    val geo: Geo? = null, //Информация о местоположении
    val singerId: Int = 0, //Идентификатор автора, если запись была опубликована от имени сообщества и подписана пользователем
    val copyHistory: Array<Any> = emptyArray(), //Массив, содержащий историю репостов для записи.
    val canPin: Boolean = true, //Информация о том, может ли текущий пользователь закрепить запись.
    val canDelete: Boolean = true, //Информация о том, может ли текущий пользователь удалить запись.
    val canEdit: Boolean = true, //Информация о том, может ли текущий пользователь редактировать запись.
    val isPinned: Boolean = true, //Информация о том, что запись закреплена
    val markedAsAds: Boolean = true, //Информация о том, содержит ли запись отметку "реклама"
    val isFavorite: Boolean = true, //true, если объект добавлен в закладки у текущего пользователя
    val postponedId: Int = 1, //Идентификатор отложенной записи. Это поле возвращается тогда, когда запись стояла на таймере
) {
    override fun equals(other: Any?): Boolean { //Сгенерировано автоматически. Зачем эти сравнения?
        if (this === other) return true
        if (other !is Post) return false

        if (id != other.id) return false
        if (ownerId != other.ownerId) return false
        if (fromId != other.fromId) return false
        if (createdBy != other.createdBy) return false
        if (date != other.date) return false
        if (text != other.text) return false
        if (replyOwnerId != other.replyOwnerId) return false
        if (replyPostId != other.replyPostId) return false
        if (friendsOnly != other.friendsOnly) return false
        if (comment != other.comment) return false
        if (copyright != other.copyright) return false
        if (likes != other.likes) return false
        if (reposts != other.reposts) return false
        if (views != other.views) return false
        if (postType != other.postType) return false
        if (postSource != other.postSource) return false
        if (!attachments.contentEquals(other.attachments)) return false
        if (geo != other.geo) return false
        if (singerId != other.singerId) return false
        if (!copyHistory.contentEquals(other.copyHistory)) return false
        if (canPin != other.canPin) return false
        if (canDelete != other.canDelete) return false
        if (canEdit != other.canEdit) return false
        if (isPinned != other.isPinned) return false
        if (markedAsAds != other.markedAsAds) return false
        if (isFavorite != other.isFavorite) return false
        if (postponedId != other.postponedId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + ownerId
        result = 31 * result + fromId
        result = 31 * result + createdBy
        result = 31 * result + date.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + replyOwnerId
        result = 31 * result + replyPostId
        result = 31 * result + friendsOnly.hashCode()
        result = 31 * result + comment.hashCode()
        result = 31 * result + copyright.hashCode()
        result = 31 * result + likes.hashCode()
        result = 31 * result + reposts
        result = 31 * result + views
        result = 31 * result + postType.hashCode()
        result = 31 * result + (postSource?.hashCode() ?: 0)
        result = 31 * result + attachments.contentHashCode()
        result = 31 * result + (geo?.hashCode() ?: 0)
        result = 31 * result + singerId
        result = 31 * result + copyHistory.contentHashCode()
        result = 31 * result + canPin.hashCode()
        result = 31 * result + canDelete.hashCode()
        result = 31 * result + canEdit.hashCode()
        result = 31 * result + isPinned.hashCode()
        result = 31 * result + markedAsAds.hashCode()
        result = 31 * result + isFavorite.hashCode()
        result = 31 * result + postponedId
        return result
    }
}

data class Views(
    val count: Int
)

data class Likes(
    val count: Int = 0, //Число пользователей, которым понравилась запись
    val userLikes: Boolean = true, //Наличие отметки «Мне нравится» от текущего пользователя
    val canLike: Boolean = true, //Информация о том, может ли текущий пользователь поставить отметку «Мне нравится»
    val canPublish: Boolean = true //Информация о том, может ли текущий пользователь сделать репост записи
)