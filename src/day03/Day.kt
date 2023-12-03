package day03

import utils.assertEquals
import utils.println
import utils.readInput


private val dir = object {}.javaClass.`package`.name

data class Num(val lineIndex: Int, val rowIndex: Int, var rowIndexEnd: Int, var num: Int)
data class Entry(val lineIndex: Int, val rowIndex: Int, val char: Char) {
    fun isDigit() = char.isDigit()
    fun isSymbol() = !char.isDigit() && char != '.'
}

fun main() {

    fun part1(input: List<String>): Int {
        val mapIndexed = input.mapIndexed { lineIndex, s ->
            s.toList().mapIndexed { rowIndex, c ->
                Entry(lineIndex, rowIndex, c)
            }
        }
        val numbers = mapIndexed.map {
            it.foldIndexed(mutableListOf<Num>()) { index, b, c ->
                val lastOrNull = b.lastOrNull()
                if (c.isDigit() && lastOrNull?.rowIndexEnd == c.rowIndex - 1) {
                    lastOrNull.num = lastOrNull.num * 10 + c.char.digitToInt()
                    lastOrNull.rowIndexEnd = c.rowIndex
                } else if (c.isDigit()) {
                    b.add(Num(c.lineIndex, c.rowIndex, c.rowIndex, c.char.digitToInt()))
                }
                b
            }
        }.flatten()
//            .take(2)//TODO
        val filter = numbers.sumOf {

            val line = (it.lineIndex - 1..it.lineIndex + 1)
            val row = (it.rowIndex - 1..it.rowIndexEnd + 1)
//            println("$it $line $row")
//            println("${line.zip(row)}")
            val map = line.map { a ->
                row.map {
                    a to it
                }
            }.flatten()
//            println("$map")
//            println("${map.size}")
            val any = map.any { (l, r) ->
//                println(
//                    "$l $r ${mapIndexed.getOrNull(l)?.getOrNull(r)?.isSymbol()?:false} ${
//                        mapIndexed.getOrNull(l)?.getOrNull(r)?.char
//                    }"
//                )
                mapIndexed.getOrNull(l)?.getOrNull(r)?.isSymbol()?:false
            }
            if(any){
//                println("${it.num}")
                it.num
            }else{
                0
            }
        }
        return filter
    }

    fun part2(input: List<String>): Int {
        val mapIndexed = input.mapIndexed { lineIndex, s ->
            s.toList().mapIndexed { rowIndex, c ->
                Entry(lineIndex, rowIndex, c)
            }
        }
        val numbers = mapIndexed.map {
            it.foldIndexed(mutableListOf<Num>()) { index, b, c ->
                val lastOrNull = b.lastOrNull()
                if (c.isDigit() && lastOrNull?.rowIndexEnd == c.rowIndex - 1) {
                    lastOrNull.num = lastOrNull.num * 10 + c.char.digitToInt()
                    lastOrNull.rowIndexEnd = c.rowIndex
                } else if (c.isDigit()) {
                    b.add(Num(c.lineIndex, c.rowIndex, c.rowIndex, c.char.digitToInt()))
                }
                b
            }
        }.flatten()
        val filter = numbers.map {

            val line = (it.lineIndex - 1..it.lineIndex + 1)
            val row = (it.rowIndex - 1..it.rowIndexEnd + 1)
//            println("$it $line $row")
//            println("${line.zip(row)}")
            val map = line.map { a ->
                row.map {
                    a to it
                }
            }.flatten()
//            println("$map")
//            println("${map.size}")
            map.mapNotNull { (l, r) ->
                if(mapIndexed.getOrNull(l)?.getOrNull(r)?.char == '*'){
                    mapIndexed.getOrNull(l)?.getOrNull(r)to it
                }else {
                    null
                }
            }
        }.flatten().groupBy { it.first }.filter { it.value.size>1 }.map {
            it.value.first().second.num* it.value[1].second.num
        }.sum()


        return filter
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("part01_test", dir)
    val part1 = part1(testInput)
    assertEquals(part1, 4361)

    val input = readInput("input", dir)
    val part1Result = part1(input)
    part1Result.println("Result 1 ---")
    assertEquals(part1Result, 530495)

    val testInput2 = readInput("part01_test", dir)
//    val testInput2 = readInput("part02_test", dir)
    val part2 = part2(testInput2)
    assertEquals(part2, 467835)

    val part2Result = part2(input)
    part2Result.println("Result 2 ---")
    assertEquals(part2Result, 80253814)
}
