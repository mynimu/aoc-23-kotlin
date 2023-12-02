package day02

import utils.assertEquals
import utils.println
import utils.readInput
import kotlin.math.max


private val dir = object {}.javaClass.`package`.name

fun main() {

    val availableSet = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )


    fun part1(input: List<String>): Int {
        return input.sumOf { inputString ->

            val match = Regex("Game (\\d+): (.*)").find(inputString)!!
            val (gameNr, games) = match.destructured
            val game = games.split(";").map {
                it.split(",").map { game ->

                    val match = Regex("(\\d+) (.*)").find(game)!!
                    val (count, color) = match.destructured

                    color to count.toInt()
                }
            }
            val invalid = availableSet.any { available ->
                game.any { currentSet ->
                    val find = currentSet.find { it.first == available.key }?.second ?: 0
//            "$find/$available/${find > available.value}"
                    find > available.value
                }
            }
            if (!invalid) gameNr.toInt() else 0

        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { inputString ->

            val match = Regex("Game (\\d+): (.*)").find(inputString)!!
            val (gameNr, games) = match.destructured
            val game = games.split(";").map {
                it.split(",").map { game ->
                    val (count, color) = Regex("(\\d+) (.*)").find(game)!!.destructured

                    color to count.toInt()
                }
            }
            game.fold(Triple(0, 0, 0)) { (red, green, blue), currentSet ->
                val redInGame = currentSet.find { it.first == "red" }?.second ?: 0
                val greenInGame = currentSet.find { it.first == "green" }?.second ?: 0
                val blueInGame = currentSet.find { it.first == "blue" }?.second ?: 0

                Triple(
                    max(red, redInGame),
                    max(green, greenInGame),
                    max(blue, blueInGame),
                )
            }.let {(red, green, blue) ->
                red*green*blue
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("part01_test", dir)
    val part1 = part1(testInput)
    assertEquals(part1, 8)

    val input = readInput("input", dir)
    val part1Result = part1(input)
    part1Result.println("Result 1 ---")
    assertEquals(part1Result, 3035)

    val testInput2 = readInput("part01_test", dir)
    val part2 = part2(testInput2)
    assertEquals(part2, 2286)

    val part2Result = part2(input)
    part2Result.println("Result 2 ---")
    assertEquals(part2Result, 66027)
}
