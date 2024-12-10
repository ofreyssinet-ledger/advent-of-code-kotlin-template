package day04

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import kotlin.math.exp

class Day04KtTest {
    private val testInput1 = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent().lines()

    private val testInput2 = testInput1

    private val part1Expected = 18
    private val part2Expected = 9

    @Test
    fun testSolveProblemPart1() {
        assertEquals(part1Expected, solveProblemPart1(testInput1))
    }

    @Test
    fun testSolveProblemPart2() {
        assertEquals(part2Expected, solveProblemPart2(testInput2))
    }

    @Test
    fun testCountOccurencesOfWord() {
        assertEquals(0, countOccurencesOfWord("XMA", "XMAS"))
        assertEquals(1, countOccurencesOfWord("XMAS", "XMAS"))
        assertEquals(1, countOccurencesOfWord("TGYUXMASTYU", "XMAS"))
        assertEquals(2, countOccurencesOfWord("TGYUXMASTYXMASU", "XMAS"))
    }

    @Test
    fun testGetInputDiagonalsA() {
        val lines = listOf(
            "ABCD",
            "EFGH",
            "IJKL"
        )
        val expected = listOf(
            "AFK",
            "EJ",
            "I",
            "BGL",
            "CH",
            "D"
        )
        assertEquals(expected, getInputDiagonalsA(lines))
    }

    @Test
    fun testGetAllAlignedStrings() {
        val lines = listOf(
            "ABCD",
            "EFGH",
            "IJKL"
        )
        val expected = listOf(
            "ABCD", // rows
            "EFGH",
            "IJKL",
            "AEI", // columns
            "BFJ",
            "CGK",
            "DHL",
            "AFK", // diag a
            "EJ",
            "I",
            "BGL",
            "CH",
            "D",
            "DGJ", // diag b
            "HK",
            "L",
            "CFI",
            "BE",
            "A",
        ).flatMap { listOf(it, it.reversed()) }.sorted()

        val result = getAllAlignedStrings(lines).sorted()
        assertEquals(expected, result)
    }

    @Test
    fun testApply3by3Mask() {
        val input = listOf(
            listOf("a", "b", "c"),
            listOf("d", "e", "f"),
            listOf("g", "h", "i")
        )
        val expected = listOf(
            "a.c",
            ".e.",
            "g.i"
        )
        assertEquals(expected, apply3by3Mask(input))
    }
}