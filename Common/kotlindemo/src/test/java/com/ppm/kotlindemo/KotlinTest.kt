package com.ppm.kotlindemo

import org.junit.Test
import java.lang.IllegalArgumentException

class KotlinTest {

    fun var1(vararg v: Int) {
        for (vt in v) {
            print(vt)
        }
    }

    @Test
    fun main() {
//        var1(1, 2, 3, 4, 5)

//        var sumLambda: (Int, Int) -> Int = { x, y -> x + y }
//        print(sumLambda(1, 3))

//        var a = 1
//        var s1 = "a is $a"
//        a = 2
//        var s2 = "${s1.replace("is", "was")}, but now is $a"
//        print(s2)
//
//        var age: String? = "23"
//        val ages = age!!.toInt()
//        val ages1 = ages?.toInt()
//        val ages2 = ages?.toInt() ?: -1
//
//        for (i in 1..4) print(i)
//
//        for (i in 4 downTo 1 step 2) print(i)
//
//        for (i in 1 until 10) print(i) // [1,10)

        val text = """
    多行字符串
    多行字符串
    """
        println(text)   // 输出有一些前置空格
    }

    fun getStringLength(obj: Any): Int {
        if (obj is String) {
            return obj.length
        }

        return 1
    }

    @Test
    fun num() {
        val a: Int = 1000
        println(a === a)
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a
        println(boxedA === anotherBoxedA)

        val x = (1 shl 2) and 0xff00ff0
    }

    fun check(c: Char) {
    }

    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'9')
            throw IllegalArgumentException("Out of range")
        return c.toInt() - '0'.toInt()
    }


    class ArrrAy<T> private constructor() {
        val size: Int = 0

        operator fun set(index: Int, value: T): Unit {}
    }

    fun testArray() {
        val asc = Array(5) { i ->
            (i * i).toString()
        }

        asc.forEach { print(it) }

        val x: IntArray = intArrayOf(1, 2, 3)
        x[0] = x[1] + x[2]

        val b: UByte = 1u
        val s: UShort = 1u
        val l: ULong = 1u

        val a1 = 42u
        val a2 = 0xffu

        val a = 1UL
    }

    @Test
    fun testString() {
        val s = "Hello World"

        for (c in s) {
            println(c)
        }

        val ss = "abc" + 1
        println(s + "def")


        //转义字符串
        val sss = "Hello, world\n"

        //原始字符串
        val text = """
            for(c in "foo")
            print(c)
        """

        val i = 10
        println("i = $i")

        val sl = "abc"

        println("$sl.length is ${sl.length}")

        println(
            """
           $('$')9.99
        """
        )
    }

    @Test
    fun loop() {
        loop@ for (i in 1..100) {
            loop1@ for (j in 1..100) {
                if (i == 50) break@loop1
            }
        }
    }

    fun foo() {
        listOf(1, 2, 3, 4, 5)
            .forEach {
                if (it == 3) return
                print(it)
            }

        listOf(1, 2, 3, 4, 5).forEach list@{
            if (it == 3) return@list
        }

        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach
            print(it)
        }

        print("done with implicit label")

        listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
            if (value == 3) return
            print(value)
        })
        print("down with anonymous function")
    }

    class InitOrderDemo(name: String) {

        val firstProperty = "First property: $name".also(::println)

        init {
            println("First initializer block that prints ${name}")
        }

        val sencodeProperty = "Second property: ${name.length}".also(::println)

        init {
            println("Seconde initializer block that prints ${name.length}")
        }
    }

    class Person(val name: String) {
        constructor(name: String, parent: Person) : this(name) {
        }
    }

    class Constructors {  //初始化代码块会在次构造函数之前执行
        init {
            println("Init block")
        }

        constructor(i: Int) {
            println("Constructors")
        }
    }

    class DontCreateMe private constructor() {}

    class Customer(val customer: String = "")


    open class Base {
        open fun v() {}
        fun nv() {}
    }

    class Derived : Base() {
        override fun v() {
        }
    }

    open class AnotherDerived : Base() {
        final override fun v() {
        }
    }

    open class Foo {
        open val x: Int
            get() {
                return x
            }
    }

    class Bar1 : Foo() {
        override val x: Int = 133
    }

    interface Foo1 {
        val count: Int
    }

    class Bar3(override val count: Int) : Foo1

    class Bar2 : Foo1 {
        override var count: Int = 0
    }


}