import org.junit.jupiter.api.Test
import kotlin.test.*

class SplayTreeTests {

    private val tree: Splay<Int, Int> = Splay()

    @Test
    fun insertionTest() {
        for (i in 1 until 50) {
            tree.insert(i, i)
        }
        assertTrue(tree.contains(11))
        assertTrue(tree.contains(2))
        assertTrue(tree.contains(37))
        assertFalse(tree.contains(0))
        assertFalse(tree.contains(51))
    }

    @Test
    fun deletionTest() {
        for (i in 1 until 50) {
            tree.insert(i, i)
        }
        tree.remove(2)
        assertFalse(tree.contains(2))
        tree.remove(34)
        assertFalse(tree.contains(34))
        tree.remove(71)
        assertFalse(tree.contains(71))
    }

    @Test
    fun containsTest() {
        for (i in 1..100) {
            tree.insert(i, i)
        }
        assertTrue(tree.contains(1))
        assertTrue(tree.contains(34))
        assertTrue(tree.contains(69))
        assertTrue(tree.contains(87))
        assertTrue(tree.contains(100))
        assertFalse(tree.contains(101))
        assertFalse(tree.contains(0))
    }

    @Test
    fun getterTest() {
        for (i in 1 until 50) {
            tree.insert(i, i)
        }
        assertEquals(1, tree.get(1))
        assertEquals(45, tree.get(45))
        assertEquals(null, tree.get(0))
    }

    @Test
    fun heightTest() {
        for (i in 0 until 20) {
            tree.insert(i, i)
        }
        assertEquals(19, tree.height())
    }

    @Test
    fun sizeTest() {
        for (i in 500 downTo 1 step 3) {
            tree.insert(i, i)
        }
        assertEquals(167, tree.size())
    }

    @Test
    fun splayTest() {
        for (i in 1..5) {
            tree.insert(i, i)
        }
        assertEquals(5, tree.getRoot(0))
        tree.insert(9, 100)
        assertEquals(100, tree.getRoot(0))
        for (i in 50 until 0 step 7) {
            tree.insert(i, i)
        }
        tree.get(2)
        assertEquals(2, tree.getRoot(0))
    }
}
