package com.ppm.kotlindemo

import org.junit.Test
import java.lang.Integer.parseInt

class FunTest {
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun sum1(a: Int, b: Int) = a + b

    @Test
    fun print() {
        printSum(a = 1, b = 2)
    }

    fun printSum(a: Int, b: Int): Unit {
        println("sum of $a and $b is ${a + b}")
    }

    fun printFeild() {
        val a: Int = 1
        val b = 2
        val c: Int
        c = 3

        var x = 5
        x += 1

        val PI = 3.14
        var x1 = 0

        fun incrementX() {
            x += 1
        }
    }

    fun printProduct(arg1: String, arg2: String) {
        val x = parseInt(arg1)
        val y = parseInt(arg2)

        if (x != null && y != null) {
            print(x * y)
        } else {
            println("either '$arg1' or '$arg2' is not a number")
        }
    }
}

