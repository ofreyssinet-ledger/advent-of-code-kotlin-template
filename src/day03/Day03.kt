package day03

import println
import readInput

fun main() {
    // Read the input from the file.
    val input = readInput("day03/data")
    println(solveProblemPart1(input))
    println(solveProblemPart2(input))
}

data class Mul(val first: Int, val second: Int) {
    fun execute() = first * second
}

fun parseMulString(string: String): Mul {
    val (_, first, second) = string.split("(", ",", ")")
    val res = Mul(first.toInt(), second.toInt())
    println("parseMulString: ${string}=$res")
    return res
}

fun extractMultiplications(memoryString: String): List<Mul> {
    return Regex("""mul\(\d*,\d*\)""")
        .findAll(memoryString)
        .toList()
        .flatMap { matchResult -> matchResult.groups.map { it?.value } }
        .also { it.println() }
        .map { parseMulString(it.toString()) }
}

fun extractMultiplicationsWithDoDont(memoryString: String): List<Mul> {
    return Regex("""mul\(\d*,\d*\)|do\(\)|don't\(\)""")
        .findAll(memoryString)
        .toList()
        .flatMap { matchResult -> matchResult.groups.map { it?.value } }
        .also { println("matches: $it") }
        .fold(Pair(true, emptyList<Mul>())) { acc, string ->
            if (string == "do()") Pair(true, acc.second)
            else if (string == "don't()") Pair(false, acc.second)
            else if (!acc.first) acc
            else Pair(true, acc.second + parseMulString(string.toString()))
        }.second
}

fun solveProblemPart1(inputLines: List<String>): Int {
    return extractMultiplications(inputLines.joinToString("\n"))
        .sumOf { it.execute() }
}

fun solveProblemPart2(inputLines: List<String>): Int {
    return extractMultiplicationsWithDoDont(inputLines.joinToString("\n"))
        .sumOf { it.execute() }
}