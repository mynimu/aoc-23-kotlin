package day04

import utils.assertEquals
import utils.println
import utils.readInput
import kotlin.math.pow


private val dir = object {}.javaClass.`package`.name


fun main() {

    fun parseGame(inp: String): Pair<Int, Int> {
        val match = Regex("Card +(\\d+): (.+)\\|(.+)").find(inp)!!
        val (card, game1, game2) = match.destructured

        val mine = game1.split(" ").filter { it != "" }
        val winning = game2.split(" ").filter { it != "" }
        val cardNr = card.toInt()
        val intersect = mine.intersect(winning)
        val winCards = intersect.size
        return Pair(cardNr, winCards)
    }

    fun part1(input: List<String>): Int {
        return input.sumOf {
            val (_, winCards) = parseGame(it)
           2f.pow(winCards-1).toInt()
        }
    }


    fun part2(input: List<String>): Int {
        val map = (1..input.size).associateWith { 1 }.toMutableMap()
        input.forEach { inp ->
            val (cardNr, winCards) = parseGame(inp)
            val plus = map[cardNr]!!
            (cardNr + 1..cardNr + winCards).forEach {
                map[it] = map[it]!! + plus
            }
        }
        return map.values.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("part01_test", dir)
    val part1 = part1(testInput)
    assertEquals(part1, 13)

    val input = readInput("input", dir)
    val part1Result = part1(input)
    part1Result.println("Result 1 ---")
    assertEquals(part1Result, 25004)

    val testInput2 = readInput("part01_test", dir)
    val part2 = part2(testInput2)
    assertEquals(part2, 30)

    val part2Result = part2(input)
    part2Result.println("Result 2 ---")
    assertEquals(part2Result, 14427616)
}
