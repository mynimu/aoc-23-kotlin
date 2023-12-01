package day01

import utils.assertEquals
import utils.println
import utils.readInput


private val dir = object {}.javaClass.`package`.name

fun main() {

    fun part1(input: List<String>): Int {
        return input.fold(0) { sum, it ->
            val first = it.first { it.isDigit() }
            val last = it.last { it.isDigit() }
            sum + (first + "" + last).toInt()
        }
    }

    val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    val allNumbers = numbers.plus((1..9).map { it.toString() })

    fun mapToNumber(foundNumber: Pair<Int, String>?) = foundNumber?.second?.let { number ->
        val indexOf = numbers.indexOf(number)
        if (indexOf == -1) {
            number
        } else {
            (indexOf + 1).toString()
        }
    }!!

    fun part2(input: List<String>): Int = input.fold(0) { sum, line ->

        val firstNumber = mapToNumber(line.findAnyOf(allNumbers))
        val lastNumber = mapToNumber(line.findLastAnyOf(allNumbers))

        sum + (firstNumber + lastNumber).toInt()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("part01_test", dir)
    val part1 = part1(testInput)
    assertEquals(part1, 142)

    val input = readInput("input", dir)
    part1(input).println()

    val testInput2 = readInput("part02_test", dir)
    val part2 = part2(testInput2)
    assertEquals(part2, 281)

    part2(input).println()
}
