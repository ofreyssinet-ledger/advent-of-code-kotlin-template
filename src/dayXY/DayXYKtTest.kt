package dayXY

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class DayXYKtTest {
    private val testInput1 = """
    """.trimIndent().lines()

    private val testInput2 = testInput1

    private val part1Expected = 0
    private val part2Expected = 0

    @Test
    fun testSolveProblemPart1() {
        assertEquals(part1Expected, solveProblemPart1(testInput1))
    }

    @Test
    fun testSolveProblemPart2() {
        assertEquals(part2Expected, solveProblemPart2(testInput2))
    }
}