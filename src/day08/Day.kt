package day08

import utils.assertEquals
import utils.println
import utils.readInput


private val dir = object {}.javaClass.`package`.name


fun main() {

    fun get1(
        instructions: List<Int>,
        result: String,
        a: Map<String, List<String>>,
        done: MutableMap<String, String>,
    ): Pair<String, Boolean> {
        val key = result + instructions

        if (done.contains(key)) {
            return done[key]!! to false
        }
        val fold = instructions.fold(result) { x, z ->
            a[x]!![z]
        }
        println("new $result ${instructions.size} ${instructions} $fold")
        done[key] = fold
        return fold to true
    }


    fun solve(
        counter: Int,
        instructions: String,
        currentInstructionIndex: Int,
        result: String,
        a: Map<String, List<String>>,
        test: (String) -> Boolean
    ): Int {
        var counter1 = counter
        var currentInstructionIndex1 = currentInstructionIndex
        var result1 = result
        while (true) {
            counter1++
            val index: Int = if (instructions.elementAt(currentInstructionIndex1) == 'L') 0 else 1
            result1 = a[result1]!![index]
            currentInstructionIndex1++
            if (currentInstructionIndex1 >= instructions.length) {
                currentInstructionIndex1 = 0
            }
            //            currentInstructionIndex = (currentInstructionIndex + 1) % instructions.size
            if (counter % 10000 == 0) {
                println("$result - $counter1 $result1 ${instructions[currentInstructionIndex1]} ${a[result1]} $currentInstructionIndex1")
            }
            if (test(result1)) {
                return counter1
            }
        }
    }

    fun part1(input: List<String>): Int {

        println(input.first())

        val instructions = input.first()
        var currentInstructionIndex = 0
        var result = "AAA"
        var counter = 0
        val a = input.drop(2).map {
//            println(it)

            //BQV = (HFG, GDR)
            val split = it.split(" = (", ", ", ")")//.map { it.trim() }.filter { it.isNotEmpty() }
//            if (result == "") {
//                result = split[0]
//            }
            val pair = split[0] to split.drop(1)
            println(pair)
            pair
        }.toMap()
        return solve(counter, instructions, currentInstructionIndex, result, a) { it == "ZZZ" }
    }


//    fun part1b(input: List<String>): Int {
//        val instructions = input.first().windowed(1).map { if (it == "L") 0 else 1 }
//        var result = ""
//        val a = input.drop(2).map {
//            val split = it.split("=", "(", ",", ")").map { it.trim() }.filter { it.isNotEmpty() }
//            if (result == "") {
//                result = split[0]
//            }
//            split[0] to split.drop(1)
//        }.toMap()
//        println("$instructions")
//
//        fun step(start: String) =
//            instructions.fold(start) { acc, leftRight ->
//                val s = a[acc]!![leftRight]
////                println("$start $s")
//
//
//                s
//            }
//
//
//        val indexOf = generateSequence(result, ::step)
////            .onEachIndexed { index, s ->  println("$index $s") }
//            .indexOf("ZZZ") * instructions.size
//        println("$indexOf")
//        return indexOf
//
//    }


    fun leastCommonMultiple(message: List<Int>): Long {
        val min = message.min()
        var cur = min.toLong()
        while (!message.all { resultX -> cur % resultX == 0L }) {
            cur += min
        }
        return cur
    }

    fun part2(input: List<String>): Long {
        val instructions = input.first()
        var currentInstructionIndex = 0
        var counter = 0
        val mapping = input.drop(2).associate { line ->
            //BQV = (HFG, GDR)
            line.split(" = (", ", ", ")").let {
                it[0] to it.drop(1)
            }
        }
        var result = mapping.keys.filter { it.endsWith("A") }
        println(result)
        val message =
            result.map { solve(counter, instructions, currentInstructionIndex, it, mapping) { it.endsWith("Z") } }
        println(message)

        return leastCommonMultiple(message)

    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("part01_test", dir)
//    val part1 = part1(testInput)
//    assertEquals(part1, 2)
//    val testInputa = readInput("part01a_test", dir)
//    val part1a = part1(testInputa)
//    assertEquals(part1a, 6)
//
    val input = readInput("input", dir)
//    val part1Result = part1(input)
//    part1Result.println("Result 1 ---")
//    assertEquals(part1Result, 12361)

    val testInput2 = readInput("part02_test", dir)

//    val testInput2 = readInput("part02_test", dir)
    val part2 = part2(testInput2)
    assertEquals(part2, 6)

    val part2Result = part2(input)
    part2Result.println("Result 2 ---")
//    assertEquals(part2Result, 1)
}
