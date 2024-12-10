package day01

import getColumn
import getColumns
import mapToInt
import readInput
import kotlin.math.abs

fun main() {
    // Read the input from the `src/Day01.txt` file.
    val input = readInput("day01/Day01")
    // println(solveProblemPart1(input))
    println(solveProblemPart2(input))
}


fun solveProblemPart1(inputLines: List<String>): Int {
    val (col0Sorted, col1Sorted) =
        listOf(getColumn(inputLines, 0), getColumn(inputLines, 1))
            .map { column ->
                column
                    .map { it.toInt() }
                    .sortedBy { it }
            }
    var sum = 0
    for ((index, value) in col0Sorted.withIndex()) {
        sum += abs(value - (col1Sorted[index]))
    }
    return sum
}

fun solveProblemPart2(inputLines: List<String>): Int {
    val (col0, col1) = getColumns(inputLines).map { mapToInt(it) }

    fun makeCountMap(inputColumn: List<Int>) =
            inputColumn.groupBy { it }
            .mapValues { it.value.size }

    val col1CountMap = makeCountMap(col1)

    var score = 0
    for (number in col0) {
        val toAdd = number * (col1CountMap[number] ?: 0)
        score += toAdd
    }
    return score
}