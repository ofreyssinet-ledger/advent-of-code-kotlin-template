package day09

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import kotlin.math.exp

class Day09KtTest {
    private val testInput1 = """
        2333133121414131402
    """.trimIndent().lines()

    private val testInput2 = testInput1

    private val part1Expected = 1928L
    private val part2Expected = 0

    @Test
    fun testSolveProblemPart1() {
        assertEquals(part1Expected, solveProblemPart1(testInput1))
    }

    @Test
    fun testSolveProblemPart2() {
        assertEquals(part2Expected, solveProblemPart2(testInput2))
    }

    @Test
    fun testExpandedMap() {
        val expected = "00...111...2...333.44.5555.6666.777.888899"
        val result = DiskMap(testInput1[0]).expandedDiskMap.split("|").joinToString("")
        assertEquals(expected, result)
    }

    @Test
    fun testRearrangedFilesExpandedDiskMapString() {
        val expected = "0099811188827773336446555566.............."
        val result = DiskMap(testInput1[0]).rearrangedFilesExpandedDiskMapString
        assertEquals(expected, result)
    }
}