
const val defaultColumnSeparator = "   "
fun getColumn(lines: List<String>, columnIndex: Int, separator: String = defaultColumnSeparator) =
    lines
        .map { it.split(separator)[columnIndex] }

fun <T>transposeMatrix(lines: List<List<T>>): List<List<T>> {
    val columnsCount = lines.maxOf { it.size }
    val columns = (0..<columnsCount).toList().map { emptyList<T>() }.toMutableList()
    for (line in lines) {
        for ((columnIndex, item) in line.withIndex()) {
            columns[columnIndex] = columns[columnIndex] + item
        }
    }
    return columns
}

fun getColumns(lines: List<String>, separator: String = defaultColumnSeparator): List<List<String>> {
    val splittedLines = lines.map { it.split(separator) }
    return transposeMatrix(splittedLines)
}

fun mapToInt(inputLines: List<String>) =
    inputLines.map { it.toInt() }