package day01

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class Day01KtTest {
    private val testInput = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent().lines()
    @Test
    fun testSolveProblemPart1() {
        val testExpectedResult = 11
        assertEquals(testExpectedResult, solveProblemPart1(testInput))
    }

    @Test
    fun testSolveProblemPart2() {
        val testExpectedResult = 31
        assertEquals(testExpectedResult, solveProblemPart2(testInput))
    }
}