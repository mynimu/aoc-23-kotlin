package day09

import utils.assertEquals
import utils.println
import utils.readInput


private val dir = object {}.javaClass.`package`.name


fun main() {

    fun parse(input: List<String>) =
        input.map { it.split(" ") }.map { it.map { it.toInt() } }

    fun List<List<Int>>.solve(): Int = this
        .sumOf { list ->
            list.fold(0 to list) { (a, l), b ->
                val zipped = l.zipWithNext().map { (a, b) -> b - a }
                val last = zipped.lastOrNull() ?: 0
                (a + last) to zipped
            }.component1() + list.last()
        }

    fun part1(input: List<String>) = parse(input).solve()

    fun part2(input: List<String>) = parse(input).map { it.reversed() }.solve()

// test if implementation meets criteria from the description, like:
    val testInput = readInput("part01_test", dir)
    val part1 = part1(testInput)
    assertEquals(part1, 114)

    val input = readInput("input", dir)
    val part1Result = part1(input)
    part1Result.println("Result 1 ---")
    assertEquals(part1Result, 1806615041)

    val testInput2 = readInput("part01_test", dir)

//    val testInput2 = readInput("part02_test", dir)
    val part2 = part2(testInput2)
    assertEquals(part2, 2)

    val part2Result = part2(input)
    part2Result.println("Result 2 ---")
    assertEquals(part2Result, 1211)
}
