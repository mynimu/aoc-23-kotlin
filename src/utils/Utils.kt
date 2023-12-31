package utils

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String, dir: String) = Path("src/$dir/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println(context: String="") = System.out.println("$context$this")


fun <T> assertEquals(first: T, expected: T) {
    println("$first / $expected")
    check(first == expected){
        "CHECK failed: $first / $expected"
    }
}
