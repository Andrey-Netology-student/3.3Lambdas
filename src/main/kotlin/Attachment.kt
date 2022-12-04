sealed class Attachment(val type: String) //Запечатанный класс

data class VideoAttachment(val video: String) : Attachment("video")

data class PhotoAttachment(val photo: String) : Attachment("photo")

data class AudioAttachment(val audio: String) : Attachment("audio")

data class DocumentAttachment(val document: String) : Attachment("document")

data class LinkAttachment(val link: String) : Attachment("link")