package com.ppm.kotlindemo

import org.junit.Test
import kotlin.text.Typography.times

class Box<T>(t: T) {
    var value = t
}

val box: Box<Int> = Box(1)

val box1 = Box(1)

interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs
}

interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0)
    val y: Comparable<Double> = x
}

interface ComparableIn<in T> {
    operator fun compareTo(other: T): Int
}

fun demo1(x: ComparableIn<Number>) {
    x.compareTo(1.0)
    val y: ComparableIn<Double> = x
}

class Array<T>(val size: Int) {

    var t: T? = null
    fun get(index: Int): T? {
        return t
    }

    fun set(index: Int, value: T) {
        t = value
    }

    fun copy(from: Array<Any>, to: Array<Any>) {
        assert(from.size == to.size)
    }

    fun fill(dest: Array<in String>, value: String) {

    }

//    fun <E> List<E>.slice(incides: IntRange): List<E> {
//        return List(1, init =)
//    }

    val <S> List<S>.penultimate: S
        get() = this[size - 1]

    val onClick: () -> Unit = {
        println("onClick")
    }

    class IntTransformer : (Int) -> Int {
        override operator fun invoke(x: Int): Int = TODO()
    }

    val intFunction: (Int) -> Int = IntTransformer()

    val a = { i: Int -> i + 1 }

    val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }

    val twoParameters: (String, Int) -> String = repeatFun

    fun runTransformation(f: (String, Int) -> String): String {
        return f("hello", 3)
    }

    val result = runTransformation(repeatFun)

}



