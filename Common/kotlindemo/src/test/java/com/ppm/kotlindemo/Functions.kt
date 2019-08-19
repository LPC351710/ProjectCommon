package com.ppm.kotlindemo

import org.junit.Test

class fun111 {

    fun <T, R> Collection.fold(initial: R, combine: (acc: R, nextElement: T) -> R)
            : R {
        var accumulator: R = initial
//        for (element: T in this) {
//            accumulator = combine(accumulator, element)
//        }
        return accumulator

    }


    val items = listOf(1, 2, 3, 4, 5)

    fun test() {
        items.fold(0, { acc: Int, i: Int ->
            println("")
            val result = acc + i
            println("result = $result")
            result
        })

        val joinedToString = items.fold("Elements", { acc, i -> acc + " " + i })

        val product = items.fold(1, Int::times)
    }


    val stringPlus: (String, String) -> String = String::plus
    val intPlus: Int.(Int) -> Int = Int::plus

    @Test
    fun printlns() {
        println(stringPlus.invoke("<-", "->"))
        println(stringPlus("Hello, ", "world!"))

        println(intPlus.invoke(1, 1))
        println(intPlus(1, 2))
        println(2.intPlus(3))
    }

    fun compare(a: String, b: String): Boolean = a.length < b.length

    val sum1 = { x: Int, y: Int -> x + y }

    val sum2: (Int, Int) -> Int = { x, y -> x + y }

    val product = items.fold(1) { acc, e -> acc * e }

    fun printlns1() {
        println(stringPlus.invoke("<-", "->"))
    }

}