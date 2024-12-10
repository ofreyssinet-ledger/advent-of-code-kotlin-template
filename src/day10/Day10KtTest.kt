package day10

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class Day10KtTest {
    private val testInput1 = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent().lines()

    private val testInput2 = testInput1

    private val part1Expected = 36
    private val part2Expected = 81

    @Test
    fun testSolveProblemPart1() {
        assertEquals(part1Expected, solveProblemPart1(testInput1))
    }

    @Test
    fun testSolveProblemPart2() {
        assertEquals(part2Expected, solveProblemPart2(testInput2))
    }
}