package day10

import readInput

fun main() {
    // Read the input from the file.
    val input = readInput("day10/data")
    println(solveProblemPart1(input))
    println(solveProblemPart2(input))
}

data class PointCoordinates(val i: Int, val j: Int)

data class SummitCoordinates(val i: Int, val j: Int)

data class TrailCoordinates(val pointCoordinates: List<PointCoordinates>)

data class TrailExplorationResult(val trailsCoordinates: Set<TrailCoordinates>, val summitsCoordinates: Set<SummitCoordinates>)

/**
 * initial idea
 * for each 0 on the map
 *      start exploring a trailhead
 *          setOf9s = explore trailhead(i,j) ->
 *              stops if (i,j) = 9, return setOf(Pair(i,j))
 *              finds all adjacent tiles that have (i,j)value+1
 *              for those, explore trailhead and add result to a set which is returned
 *
 *          trailhead score = setOf9s.size
 */

fun exploreTrail(map: List<List<Int>>, coordinates: PointCoordinates, trailBeginning: List<PointCoordinates>): TrailExplorationResult {
    val (i, j) = coordinates

    val trail = trailBeginning + coordinates

    if (map[i][j] == 9) {
        val trailsCoordinates = setOf(TrailCoordinates(trail))
        val summitsCoordinates = setOf(SummitCoordinates(i, j))
        return TrailExplorationResult(trailsCoordinates, summitsCoordinates)
    }

    val adjacentPositions = emptyList<PointCoordinates>().toMutableList()
    // top
    if (i > 0)
        adjacentPositions += PointCoordinates(i-1, j)
    // bottom
    if (i < map.size - 1)
        adjacentPositions += PointCoordinates(i+1, j)
    // left
    if (j > 0)
        adjacentPositions += PointCoordinates(i, j-1)
    // right
    if (j < map[0].size - 1)
        adjacentPositions += PointCoordinates(i, j+1)

    // filter adjacent positions that have the current position's value + 1
    val nextToExplore = adjacentPositions.filter {
        map[it.i][it.j] == map[i][j] + 1
    }

    var summits = emptySet<SummitCoordinates>().toMutableSet()
    var trails = emptySet<TrailCoordinates>().toMutableSet()
    for (coordinate in nextToExplore) {
        val (newTrails, newSummits) = exploreTrail(map, coordinate, trail)
        trails += newTrails
        summits += newSummits
    }
    return TrailExplorationResult(trails, summits)
}

data class TrailheadScore(val summitCount: Int, val trailsCount: Int)

fun getTrailheadScore(map: List<List<Int>>, coordinates: PointCoordinates): TrailheadScore {
    val (i, j) = coordinates
    if (map[i][j] != 0) return TrailheadScore(0, 0)
    val trailExplorationResult = exploreTrail(map, coordinates, listOf(coordinates))
    return TrailheadScore(trailExplorationResult.summitsCoordinates.size, trailExplorationResult.trailsCoordinates.size)
}

fun convertInputLinesToTrailMap(inputLines: List<String>): List<List<Int>> =
    inputLines.map {line ->
        line
            .split("")
            .filter(String::isNotEmpty)
            .map { value -> value.toInt() }
    }

fun solveProblemPart1(inputLines: List<String>): Int {
    val map = convertInputLinesToTrailMap(inputLines)
    var sum = 0
    for (i in map.indices) {
        for (j in map[0].indices) {
            sum += getTrailheadScore(map, PointCoordinates(i, j)).summitCount
        }
    }
    return sum
}

fun solveProblemPart2(inputLines: List<String>): Int {
    val map = convertInputLinesToTrailMap(inputLines)
    var sum = 0
    for (i in map.indices) {
        for (j in map[0].indices) {
            sum += getTrailheadScore(map, PointCoordinates(i, j)).trailsCount
        }
    }
    return sum
}