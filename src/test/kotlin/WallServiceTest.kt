import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }
    @After
    fun print() {
        WallService.printAll()
    }

    @Test
    fun testAdd() {
        val post = Post(id = 0, text = "Message", copyright = "some text")
        val result = WallService.add(post)

        assertNotEquals(post.id, result.id)
        assertEquals(post.text, result.text)
    }

    @Test
    fun addMultiple() {
        val post = Post(id = 0, text = " ", copyright = "some")
        WallService.add(post)
        val result = WallService.add(post)

        assertNotEquals(post.id, result.id)
        assertEquals(post.text, result.text)

    }

    @Test
    fun testUpdateTrue() {
        val post = Post(id = 0, text = "update", copyright = "text")
        val addedId = WallService.add(post).id
        val updatePost = Post(addedId, text = "Hello", copyright = "some_text")
        val result = WallService.update(updatePost)

        assertEquals(true, result)
    }

    @Test
    fun updateFalse() {
        val post = Post(id = 0, text = " ", copyright = "some+text")
        val addedId = WallService.add(post).id
        val updatePost = Post(addedId + 1, text = "Hello", copyright = "some - text")
        val result = WallService.update(updatePost)

        assertEquals(false, result)
    }
}
//gradle test jacocoTestReport