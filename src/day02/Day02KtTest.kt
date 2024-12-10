package day02

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class Day02KtTest {
    private val testInput = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent().lines()

    private val part1Expected = 2
    private val part2Expected = 4

    @Test
    fun testSolveProblemPart1() {
        assertEquals(part1Expected, solveProblemPart1(testInput))
    }

    @Test
    fun testSolveProblemPart2() {
        assertEquals(part2Expected, solveProblemPart2(testInput))
    }

    @Test
    fun testTestIsSafe() {
        assertEquals(true, testIsSafe(listOf(7, 6, 4, 2, 1)))
        assertEquals(false, testIsSafe(listOf(1, 2, 7, 8, 9)))
        assertEquals(false, testIsSafe(listOf(9, 7, 6, 2, 1)))
        assertEquals(false, testIsSafe(listOf(1, 3, 2, 4, 5)))
        assertEquals(false, testIsSafe(listOf(8, 6, 4, 4, 1)))
        assertEquals(true, testIsSafe(listOf(1, 3, 6, 7, 9)))
    }

    @Test
    fun testTestReportWithRetry() {
        assertEquals(true, testReportStringWithOnePossibleRemoval("7 6 4 2 1"))
        assertEquals(false, testReportStringWithOnePossibleRemoval("1 2 7 8 9"))
        assertEquals(false, testReportStringWithOnePossibleRemoval("9 7 6 2 1"))
        assertEquals(true, testReportStringWithOnePossibleRemoval("1 3 2 4 5"))
        assertEquals(true, testReportStringWithOnePossibleRemoval("8 6 4 4 1"))
        assertEquals(true, testReportStringWithOnePossibleRemoval("1 3 6 7 9"))
    }
}