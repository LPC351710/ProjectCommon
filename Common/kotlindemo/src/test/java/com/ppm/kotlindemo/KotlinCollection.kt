package com.ppm.kotlindemo

import org.hamcrest.core.StringContains
import org.junit.Test

class Func {
    fun List<String>.getShortWordsTo(shortWords: MutableList<String>, maxLength: Int) {
        this.filterTo(shortWords) { it.length <= maxLength }

        val articles = setOf("A", "H", "E", "L", "L")
        shortWords -= articles
    }

    @Test
    fun mains() {
        val words = "A long time ago in a galaxy far far away".split(" ")
        val shortWords = mutableListOf<String>()
        words.getShortWordsTo(shortWords, 3)
        println(shortWords)
    }

    fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) {
        println("fun foo")
    }

    @Test
    fun testFun() {
        foo(1) { println("hello") }
        foo(qux = { println("qux") })
        foo { println("hello") }
        foo1(string = *arrayOf("a", "h"))
        foo1("a", "h")
    }

    fun reformat(
        str: String, normalizeCase: Boolean = true,
        upperCaseFirstLetter: Boolean = false, wordSeparator: Char = ' '
    ) {
    }

    private fun foo1(vararg string: String) {
        string.forEachIndexed { _, s ->
            println(s)
        }
    }

    fun printHello(name: String?): Unit {
        if (name != null) {
            println("Hello ${name}")
        } else {
            println("Hi there")
        }

    }

    fun double(x: Int): Int = x * 2

    fun <T> asList(vararg ts: T): List<T> {
        val result = ArrayList<T>()
        for (t in ts) {
            result.add(t)
        }
        return result
    }

    val list = asList(1, 2, 3)

    infix fun Int.shl(x: Int) {
    }

    class MyStringCollection {

        infix fun add(s: String) {}

        fun build() {
            this add "abc"
            add("abc")
        }
    }

    @Test
    fun coll() {
        val numbers = setOf(1, 2, 3)
        println(numbers.map { it * 3 })
        println(numbers.mapIndexed { index, i -> i * index })

        println(numbers.mapNotNull { if (it == 2) null else it * 3 })
        println(numbers.mapIndexedNotNull { index, i -> if (index == 0) null else i * index })

        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
        println(numbersMap.mapKeys { it.key.toUpperCase() })
        println(numbersMap.mapValues { it.value + it.key.length })

        val colors = listOf("red", "brown", "grey")
        val animals = listOf("fox", "bear", "wolf")
        println(colors zip animals)

        val anim = listOf("fox", "bear")
        println(colors zip anim)

        val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
        println(numberPairs.unzip())

        val numbers1 = listOf("one", "two", "there", "four")
        println(numbers1.associateBy { it.first().toUpperCase() })
        println(numbers1.associateBy(keySelector = { it.first().toUpperCase() }, valueTransform = { it.length }))

//        val names = listOf("Alice Adams", "Brian Brown", "Clara")
//        println(names.associate { name -> parseFullName(name).let { it.lastName to it.firstName } })

    }



}


