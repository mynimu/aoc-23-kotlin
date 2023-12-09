package day05

import utils.assertEquals
import utils.println
import utils.readInput
import java.lang.IllegalStateException


private val dir = object {}.javaClass.`package`.name

val kotlin.ranges.LongRange.min
    get() = minOf(start, endInclusive)

val kotlin.ranges.LongRange.max
    get() = maxOf(start, endInclusive)

val kotlin.ranges.LongRange.size
    get() = max - min

infix fun LongRange.intersect(other: LongRange)
        = if (min <= other.max && other.min <= max)
    maxOf(min, other.min).rangeTo(minOf(max, other.max))
else
    null

infix fun LongRange.subtractBefore(other: LongRange)
        = if (other.min in (min + 1)..max)
    min until other.min
else
    null

infix fun LongRange.subtractAfter(other: LongRange)
        = if (other.max in (min + 1) until max)
    other.max until max
else
    null

fun main() {

    data class Mapping(val destinationStart: Long, val sourceStart: Long, val range: Long) {
        val from = LongRange(sourceStart, sourceStart + range - 1)
        val offset = destinationStart - sourceStart
    }

    fun longs(
        seeds: List<Long>,
        currentMapping: MutableList<Mapping>
    ) = seeds.map { seed ->
//        println(seed)
        val mapNotNull = currentMapping.mapNotNull {
            //                            println("" + seed + "/" + LongRange(it.sourceStart, it.sourceStart + it.range))
            //                            if((it.sourceStart + it.range)<it.sourceStart){
            //                                throw IllegalStateException()
            //                            }
            //                            if((it.destinationStart + it.range)<it.destinationStart){
            //                                throw IllegalStateException()
            //                            }
            if (seed.toString() == "51515303") {

                println("QQQ $it ${it.destinationStart} + $seed - ${it.sourceStart}")
            }
            if (seed in it.sourceStart until it.sourceStart + it.range) {
                val l = it.destinationStart + seed - it.sourceStart
                l
            } else null
        }
        if (mapNotNull.size > 1) {
            throw IllegalStateException()
        }
        mapNotNull.firstOrNull() ?: seed
    }



    fun longs2(
        seeds: List<LongRange>,
        currentMappings: MutableList<Mapping>
    ): List<LongRange> = seeds.flatMap { seed ->
        val mapNotNull = currentMappings
            .mapNotNull { mapping ->
                val let = seed.intersect(mapping.from)
                    ?.let { LongRange(it.min(), it.max()) }
                if (let != null) {
                    val listOfNotNull = listOfNotNull(
                        seed.subtractBefore(mapping.from),
                        seed.subtractAfter(mapping.from),
                        LongRange(let.first + mapping.offset, let.last + mapping.offset)

                    )
                    listOfNotNull
                } else null
            }
        mapNotNull.flatten().ifEmpty { listOf( seed) }
    }

    fun part1(input: List<String>): Long {
        var seeds = input[0].split(":")[1].split(" ").filter { it.isNotBlank() && it.isNotEmpty() }.map { it.toLong() }
        println(seeds)
        var type: String? = null
        val drop = input.drop(2)
        val currentMapping = emptyList<Mapping>().toMutableList()
        val a = drop.forEach { s ->
            when {
                s.firstOrNull()?.isDigit() == true -> {
                    val destinationStart = s.split(" ")
                    currentMapping.add(
                        Mapping(
                            destinationStart[0].toLong(),
                            destinationStart[1].toLong(),
                            destinationStart[2].toLong()
                        )
                    )
                    if (destinationStart[0].toLong().toString() != destinationStart[0]) {
                        throw IllegalStateException()
                    }
                    if (destinationStart[1].toLong().toString() != destinationStart[1]) {
                        throw IllegalStateException()
                    }
                    if (destinationStart[2].toLong().toString() != destinationStart[2]) {
                        throw IllegalStateException()
                    }
                    println("aaa $s $currentMapping")
                }

                s.firstOrNull() != null -> {
//                    println("CHECK")
                    println(seeds)
                    println(currentMapping)
                    seeds = longs(seeds, currentMapping)
//                    println("CLEAR")
                    currentMapping.clear()
                }
            }

        }
        println(seeds)
        seeds = longs(seeds, currentMapping)
        println(seeds)

        return seeds.min()
    }


    fun part2(input: List<String>): Long {
        val prepSeeds =
            input[0].split(":")[1].split(" ").filter { it.isNotBlank() && it.isNotEmpty() }.map { it.toLong() }
        var seeds = prepSeeds.windowed(2, 2)
            .map { LongRange(it.first(), it.first() + it[1] - 1) }
//        println("BBB" + seeds)

        val drop = input.drop(2)
        val currentMapping = emptyList<Mapping>().toMutableList()
        drop.forEach { s ->
            when {
                s.firstOrNull()?.isDigit() == true -> {
                    val destinationStart = s.split(" ")
                    currentMapping.add(
                        Mapping(
                            destinationStart[0].toLong(),
                            destinationStart[1].toLong(),
                            destinationStart[2].toLong()
                        )
                    )
//                    println("aaa $s $currentMapping")
                }

                s.firstOrNull() != null -> {

//                    println("ABBB" + seeds)
                    val newSeeds = longs2(seeds, currentMapping)
                    seeds = newSeeds

                    println(seeds)
//                    println("CBBB" + seeds)
                    currentMapping.clear()
                }
            }

        }
        println(seeds)
        seeds = longs2(seeds, currentMapping)
        println(seeds)

        return seeds.minOf { it.first }
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("part01_test", dir)
//    val part1 = part1(testInput)
//    assertEquals(part1, 35)

    val input = readInput("input", dir)
//    val part1Result = part1(input)
//    part1Result.println("Result 1 ---")
//    assertEquals(part1Result, 309796150)

    val testInput2 = readInput("part01_test", dir)

//    val testInput2 = readInput("part02_test", dir)
    val part2 = part2(testInput2)
    assertEquals(part2, 46)

    val part2Result = part2(input)
    part2Result.println("Result 2 ---")
////    assertEquals(part2Result, 1)
}


