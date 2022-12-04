import exceptions.CommentNotFoundException
import exceptions.NoteNotFoundException

import exceptions.*
import note.Note
import note.NoteService
import org.junit.Test
import org.junit.Assert.*
//Домашнее задание к занятию «3.2. Generics и коллекции»

class NoteServiceTest {
    @Test
fun getNoteByIdNoZero() {
    val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
    val newNote:Note = NoteService.getById(note.id)
    val result = note === newNote
    assertTrue(result)
}

    @Test
    fun deleteCommentShouldNotThrow() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val comment = NoteService.createComment(note.id, Comment(0, note.id, 0, "some thread", 0, 1,0,false))
        val result = NoteService.deleteComment(note.id, comment.id)
        assertTrue(result)
    }
    @Test
    fun editCommentShouldNotThrow() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val comment = NoteService.createComment(note.id, Comment(0, note.id, 0, "some thread", 0, 1,0,false))
        val newComment = comment.copy(text = "New text")
        NoteService.editComment(note.id, comment.id, newComment)
        assertTrue(newComment in note.comments)
    }
    @Test(expected = NoteNotFoundException::class)
    fun restoreCommentShodThrowNoteNotFoundException() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val comment = NoteService.createComment(note.id, Comment(0, note.id, 0, "some thread", 0, 1,0,false))
        val isDeleted = NoteService.deleteComment(note.id, comment.id)
        val falseNoteId = note.id + 1
        if (isDeleted) NoteService.restoreComment(falseNoteId, comment.id)
    }
    @Test
    fun restoreCommentNoZero() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val comment = NoteService.createComment(note.id, Comment(0, note.id, 0, "some thread", 0, 1,0,false))
        val isDeleted = NoteService.deleteComment(note.id, comment.id)
        if (isDeleted) {
            val result = NoteService.restoreComment(note.id, comment.id)
            assertTrue(result)
        }
    }




    @Test
    fun addNoteNoZero() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        assertNotEquals(0, note.id)
    }

    @Test
    fun updateNoteShouldNotThrow() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val exists = NoteService.edit(note.id, note.copy(text = "another text"))
        assertTrue(exists)
    }

    @Test(expected = NoteNotFoundException::class)
    fun updateNoteShouldThrowNoteNotFoundException() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val falseNoteId = note.id + 1
        NoteService.edit(falseNoteId, note.copy(text = "another text"))
    }

    @Test
    fun commentShouldNotThrow() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val comment = NoteService.createComment(note.id, Comment(0, note.id, 0, "some thread", 1,1,0,true))
        NoteService.get().last().id
        val returnComment = NoteService.getComments(note.id).last()
        assertEquals(comment, returnComment)
    }

    @Test(expected = NoteNotFoundException::class)
    fun commentShouldThrow() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val falsePostId = note.id + 1
        val comment = Comment(0, falsePostId, 0, "some thread", 0, 1,0,true)
        NoteService.createComment(falsePostId, comment)
    }

    @Test
    fun deleteNoteShouldNotThrow() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val result = NoteService.delete(note.id)
        assertTrue(result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNoteShouldThrow() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val noteId = note.id
        NoteService.delete(noteId)
        NoteService.delete(noteId)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteCommentShouldThrowNoteNotFoundException() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val falseNoteId = note.id + 1
        val comment = NoteService.createComment(falseNoteId, Comment(0, falseNoteId, 0, "some thread", 0, 1,0,true))
        NoteService.deleteComment(note.id, comment.id)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentShouldThrowCommentNotFoundException() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val comment = NoteService.createComment(note.id, Comment(0, note.id, 0, "some thread", 0, 1,0,true))
        val falseCommentId = comment.id + 1
        NoteService.deleteComment(note.id, falseCommentId)
    }

    @Test
    fun editNoteShouldNotThrow() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val newNote = note.copy(text = "New text")
        NoteService.edit(note.id, newNote)
        assertTrue(newNote in NoteService.get())
    }

    @Test(expected = NoteNotFoundException::class)
    fun editNoteShouldThrow() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val newNote = note.copy(text = "New text")
        val falseNoteId = note.id + 1
        NoteService.edit(falseNoteId, newNote)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editCommentShouldThrowNoteNotFoundException() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val comment = NoteService.createComment(note.id, Comment(0, note.id, 0, "some thread", 0, 1,0,true))
        val newComment = comment.copy(text = "New text")
        val falseNoteId = note.id + 1
        NoteService.editComment(falseNoteId, comment.id, newComment)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentShouldThrowCommentNotFoundException() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val comment = NoteService.createComment(note.id, Comment(0, note.id, 0, "some thread", 0, 1,0,true))
        val newComment = comment.copy(text = "New text")
        val falseCommentId = comment.id + 1
        NoteService.editComment(note.id, falseCommentId, newComment)
    }

    @Test
    fun getNoteNoZero() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val notes = NoteService.get()
        val result = note === notes.last()
        assertTrue(result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getNoteByIdShodThrow() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val falseNoteId = note.id + 1
        NoteService.getById(falseNoteId)
    }

    @Test
    fun getCommentNoZero() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val comment = NoteService.createComment(note.id, Comment(0, note.id, 0, "some thread", 0, 1,0,true))
        val comments = NoteService.getComments(note.id)
        val result = comment === comments.last()
        assertTrue(result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentShodThrow() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val falseNoteId = note.id + 1
        NoteService.getComments(falseNoteId)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentShodThrowCommentNotFoundException() {
        val note = NoteService.add(Note(0, "Title", "Text", 0, 0, "text", "text"))
        val comment = NoteService.createComment(note.id, Comment(0, note.id, 0, "some thread", 0, 1,0,true))
        val isDeleted = NoteService.deleteComment(note.id, comment.id)
        val falseCommentId = comment.id + 1
        if (isDeleted) NoteService.restoreComment(note.id, falseCommentId)
    }
}