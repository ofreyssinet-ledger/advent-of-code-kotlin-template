package day04

import readInput
import transposeMatrix
import kotlin.math.min

fun main() {
    // Read the input from the file.
    val input = readInput("day04/data")
    println(solveProblemPart1(input))
    println(solveProblemPart2(input))
}

fun getInputLines(input: List<String>): List<String> = input
fun getInputColumns(input: List<String>): List<String> = transposeMatrix(input.map { it.split("") }).map { it.joinToString("")}

fun getInputDiagonalsA(input: List<String>): List<String> {
    val rowsCount = input.count()
    val columnCount = input[0].length
    val asMatrix = input.map { it.split("").filter(String::isNotEmpty) }
    val lines = emptyList<String>().toMutableList()

    // map on lines
    for (rowIndex in 0..<rowsCount) {
        // i is the starting char of the line
        var line = ""
        // then there are rowsCount - i remaining chars
        for (columnIndex in 0..<min(columnCount, rowsCount-rowIndex)) {
            val char = asMatrix[rowIndex + columnIndex][columnIndex]
            line += char
        }
        lines += line
    }

    // map on rows
    for (columnIndex in 1..<columnCount) {
        var line = ""
        for (rowIndex in 0..<min(rowsCount, columnCount-columnIndex)) {
            val char = asMatrix[rowIndex][rowIndex + columnIndex]
            line += char
        }
        lines += line
    }

    return lines
}

fun getInputDiagonalsB(input: List<String>): List<String> =
    getInputDiagonalsA(input.map(String::reversed))

fun countOccurencesOfWord(input: String, word: String): Int {
    val regex = Regex(word)
    return regex
        .findAll(input)
        .toList()
        .flatMap { it.groups.map { group -> group?.value } }
        //.also { println("matches: $it")}
        .count { it != null }
}

fun getAllAlignedStrings(inputLines: List<String>): List<String> =
        listOf(
            getInputLines(inputLines),
            getInputColumns(inputLines),
            getInputDiagonalsA(inputLines),
            getInputDiagonalsB(inputLines),
        )
            .flatMap { listOf(it, it.map(String::reversed) ) }
            .flatten()
            .filter(String::isNotEmpty)

val mask3by3 = listOf(
    listOf(1,0,1),
    listOf(0,1,0),
    listOf(1,0,1)
)

val possibleXMas = listOf(
    """
        M.S
        .A.
        M.S
    """.trimIndent(),
    """
        M.M
        .A.
        S.S
    """.trimIndent(),
    """
        S.M
        .A.
        S.M
    """.trimIndent(),
    """
        S.S
        .A.
        M.M
    """.trimIndent()
    )

fun apply3by3Mask(inputMatrix: List<List<String>>): List<String> {
    var lines = emptyList<String>()
    for (i in 0..2) {
        var line = ""
        for (j in 0..2) {
            val maskValue = mask3by3[i][j]
            val charAtPos = inputMatrix[i][j]
            var char = if (maskValue == 1) charAtPos else "."
            line += char
        }
        lines = lines.plus(line)
    }
    return lines
}

fun extract3by3StringMatrix(row: Int, column: Int, inputList: List<String>): List<List<String>> {
    var stringMatrix = emptyList<List<String>>()
    for (i in row..<row+3) {
        var line = inputList[i].drop(column).take(3).split("").filter(String::isNotEmpty)
        stringMatrix = stringMatrix.plusElement(line)
    }
    return stringMatrix
}


fun solveProblemPart1(inputLines: List<String>): Int {
    var allPossibleLines = getAllAlignedStrings(inputLines)

    return allPossibleLines.sumOf {
        countOccurencesOfWord(it, "XMAS")
    }
}

fun solveProblemPart2(inputLines: List<String>): Int {
    val columnsCount = inputLines[0].length
    val rowsCount = inputLines.size

    // println("possible xmas: ${possibleXMas}")

    var count = 0
    for (i in 0..rowsCount-3) {
        for (j in 0..columnsCount-3) {
            var extractedMatrix = extract3by3StringMatrix(i, j, inputLines)
            var withMaskApplied = apply3by3Mask(
                extractedMatrix
            )
            // println(withMaskApplied)
            var isXmas = possibleXMas.any { it.lines().equals(withMaskApplied) }
            if (isXmas) count+=1
        }
    }

    return count
}