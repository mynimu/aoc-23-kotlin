package day02

import utils.assertEquals
import utils.println
import utils.readInput


private val dir = object {}.javaClass.`package`.name

fun main() {

    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("part01_test", dir)
    val part1 = part1(testInput)
    assertEquals(part1, 111)

    val input = readInput("input", dir)
    part1(input).println()

    val testInput2 = readInput("part02_test", dir)
    val part2 = part2(testInput2)
    assertEquals(part2, 111)

    part2(input).println()
}
