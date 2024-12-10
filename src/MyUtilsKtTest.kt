import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.math.exp

class MyUtilsKtTest {

    @Test
    fun testGetColumn() {
        val input = listOf("a 1", "b 2", "c 3")
        val expectedColumn0 = listOf("a", "b", "c")
        val expectedColumn1 = listOf("1", "2", "3")
        assertEquals(expectedColumn0, getColumn(input, 0, " "))
        assertEquals(expectedColumn1, getColumn(input, 1, " "))
    }

    @Test
    fun testGetColumns() {
        val inputLines = """
            a 1
            b 2
            c 3
            d 4
        """.trimIndent().lines()
        val expected = listOf(listOf("a", "b", "c", "d"), listOf("1", "2", "3", "4"))
        assertEquals(expected, getColumns(inputLines, " "))
    }

    @Test
    fun testMapToInt() {
        val input = listOf("1", "22", "30")
        val expected = listOf(1, 22, 30)
        assertEquals(expected, mapToInt(input))
    }
}