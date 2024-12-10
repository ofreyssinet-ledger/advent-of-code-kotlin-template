package day02

import mapToInt
import readInput
import kotlin.math.abs

fun main() {
    // Read the input from the file.
    val input = readInput("day02/data")
    println(solveProblemPart1(input))
    println(solveProblemPart2(input))
}

fun testIsSafe(list: List<Int>): Pair<Boolean, Int> {
    var direction: Int? = null
    var prev: Int? = null
    for ((index, item) in list.withIndex()) {
        if (prev != null) {
            val diffWithPrev = item - prev
            if (abs(diffWithPrev) < 1 || abs(diffWithPrev) > 3) return Pair(false, index) // diff not in range
            val newDirection = abs(diffWithPrev) / diffWithPrev
            if (direction != null && direction != newDirection) return Pair(false, index) // direction changed
            direction = newDirection
        }
        prev = item
    }
    return Pair(true, -1)
}

fun convertReportStringToInt(reportLine: String): List<Int> = mapToInt(reportLine.split(" "))
fun testReportString(reportLine: String): Pair<Boolean, Int> =
    testIsSafe(convertReportStringToInt(reportLine))

fun testReportStringWithOnePossibleRemoval(reportLine: String): Boolean {
    val report = convertReportStringToInt(reportLine)
    val (firstTry, indexToRemove) = testIsSafe(report)
    if (firstTry) return true
    for (i in -1..1) {
        if (indexToRemove + i > 0 && indexToRemove + i < report.size) {
            val correctedReport = report.toMutableList()
            correctedReport.removeAt(indexToRemove + i)
            if (testIsSafe(correctedReport).first) return true
        }
    }
    return false
}

fun testReportStringWithOnePossibleRemovalBruteforce(reportLine: String): Boolean {
    val report = convertReportStringToInt(reportLine)
    val (firstTry, indexToRemove) = testIsSafe(report)
    if (firstTry) return true
    for (i in report.indices) {
        val correctedReport = report.toMutableList()
        correctedReport.removeAt(i)
        if (testIsSafe(correctedReport).first) return true
    }
    return false
}


fun solveProblemPart1(inputLines: List<String>): Int {
    return inputLines.count { testReportString(it).first }
}

fun solveProblemPart2(inputLines: List<String>): Int {
    return inputLines.count { testReportStringWithOnePossibleRemovalBruteforce(it) }
}