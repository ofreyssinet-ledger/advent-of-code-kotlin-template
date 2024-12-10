package day03

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class Day03KtTest {
    private val testInput1 = """
        xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
    """.trimIndent().lines()

    private val testInput2 = """
        xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
    """.trimIndent().lines()

    private val part1Expected = 161
    private val part2Expected = 48

    @Test
    fun testSolveProblemPart1() {
        assertEquals(part1Expected, solveProblemPart1(testInput1))
    }

    @Test
    fun testSolveProblemPart2() {
        assertEquals(part2Expected, solveProblemPart2(testInput2))
    }

    @Test
    fun testParseMulString() {
        assertEquals(Mul(1, 8), parseMulString("mul(1,8)"))
        assertEquals(Mul(18, 64), parseMulString("mul(18,64)"))
    }

    @Test
    fun testExtractMultiplications() {
        assertEquals(listOf(Mul(1, 8)), extractMultiplications("mul(1,8)"))
        assertEquals(listOf(Mul(2,4), Mul(5,5), Mul(11,8), Mul(8,5)), extractMultiplications(testInput1[0]))
    }
}