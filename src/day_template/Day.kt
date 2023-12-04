package day_template

import utils.assertEquals
import utils.println
import utils.readInput


private val dir = object {}.javaClass.`package`.name


fun main() {

    fun part1(input: List<String>): Int {
//        return
        input.size

        return 1
    }


    fun part2(input: List<String>): Int {
//        return
        input.size

        return 1

    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("part01_test", dir)
    val part1 = part1(testInput)
    assertEquals(part1, 0)

    val input = readInput("input", dir)
    val part1Result = part1(input)
    part1Result.println("Result 1 ---")
//    assertEquals(part1Result, 1)

    val testInput2 = readInput("part01_test", dir)

//    val testInput2 = readInput("part02_test", dir)
    val part2 = part2(testInput2)
    assertEquals(part2, 1)

    val part2Result = part2(input)
    part2Result.println("Result 2 ---")
//    assertEquals(part2Result, 1)
}
