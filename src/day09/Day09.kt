package day09

import readInput

fun main() {
    // Read the input from the file.
    val input = readInput("day09/data")
    println(solveProblemPart1(input))
    println(solveProblemPart2(input))
}

sealed class DiskBlock {
    data object FreeBlock : DiskBlock()
    data class FileBlock(val id: Int): DiskBlock()
}

data class DiskMap(val compactDiskMap: String) {
    private val compactDiskMapSplitted: List<Int> by lazy {
        compactDiskMap.split("").filter(String::isNotEmpty).map(String::toInt)
    }

    enum class MapPointerType {
        FileSize,
        FreeSpaceSize;
        fun switchPointerType(): MapPointerType =
            when(this) {
                FileSize -> FreeSpaceSize
                FreeSpaceSize -> FileSize
            }
    }

    data class CompactDiskMapCursor(val fileId: Int, val pointerType: MapPointerType, val output: String)

    /**
     * For a compactDiskMap "12345"
     * it will be "0|.|.|1|1|1|.|.|.|.|2|2|2|2|2"
     */
    val expandedDiskMap: String by lazy {
        val cursor = CompactDiskMapCursor(
            0,
            MapPointerType.FileSize,
            ""
        )
        compactDiskMapSplitted.fold(cursor) { cursor, size ->
            val nextFileId = if (cursor.pointerType == MapPointerType.FileSize) cursor.fileId + 1 else cursor.fileId
            val stringToConcat = when(cursor.pointerType) {
                MapPointerType.FreeSpaceSize -> ".|".repeat(size)
                MapPointerType.FileSize -> {
                    "${cursor.fileId}|".repeat(size)
                }
            }
            val nextString = cursor.output + stringToConcat
            CompactDiskMapCursor(
                nextFileId,
                cursor.pointerType.switchPointerType(),
                nextString
            )
        }.output
    }

    private val parsedExpandedDiskMap: List<DiskBlock> by lazy {
        expandedDiskMap.split("|").filter(String::isNotEmpty).map {
            when(it) {
                "." -> DiskBlock.FreeBlock
                else -> DiskBlock.FileBlock(it.toInt())
            }
        }
    }

    val rearrangedFilesExpandedDiskMap: List<DiskBlock> by lazy {
        val list = parsedExpandedDiskMap.toMutableList()

        val fileBlocksIndices = list
            .withIndex()
            .filter { it.value is DiskBlock.FileBlock }
            .map { it.index }
            .toMutableList()

        val fileBlocksCount = list
            .count { it is DiskBlock.FileBlock }
        val lastFileBlockIndex = list
            .withIndex()
            .findLast { it.value is DiskBlock.FileBlock }
            ?.index ?: -1

        val freeBlocksToNotMoveCount = list.withIndex().count { indexedValue ->
            indexedValue.value is DiskBlock.FreeBlock
                    && indexedValue.index > fileBlocksCount - 1
                    && indexedValue.index < lastFileBlockIndex
        }

        val numberOfBlocksToMove = lastFileBlockIndex + 1 - fileBlocksCount - freeBlocksToNotMoveCount

        println("list.size: ${list.size}, lastFileBlockIndex: $lastFileBlockIndex, fileBlocksCount: $fileBlocksCount, freeBlocksToNotMoveCount: $freeBlocksToNotMoveCount, numberOfBlocksToMove: $numberOfBlocksToMove")

        var numberOfBlocksMoved = 0
        for (i in list.indices) {
            val block = list[i]
            if (block is DiskBlock.FreeBlock) {
                val fileBlockIndex = fileBlocksIndices.removeLast()
                val fileBlock = list[fileBlockIndex]
                list[i] = fileBlock
                list[fileBlockIndex] = block
                numberOfBlocksMoved+=1
                if (numberOfBlocksMoved == numberOfBlocksToMove) return@lazy list
            }
        }

        list
    }

    val rearrangedFilesExpandedDiskMapString: String by lazy {
        rearrangedFilesExpandedDiskMap.joinToString("") {
            when (it) {
                is DiskBlock.FreeBlock -> "."
                is DiskBlock.FileBlock -> it.id.toString()
            }
        }
    }

    val rearrangedFilesChecksum: Long by lazy {
        rearrangedFilesExpandedDiskMap.withIndex().sumOf { indexedValue ->
            val (index, value) = indexedValue
            when(value) {
                is DiskBlock.FreeBlock -> 0L
                is DiskBlock.FileBlock -> value.id.toLong() * index
            }
        }
    }
}

fun solveProblemPart1(inputLines: List<String>): Long {
    println(inputLines)
    return DiskMap(inputLines[0]).rearrangedFilesChecksum
}

fun solveProblemPart2(inputLines: List<String>): Int {
    return 0
}