package com.ppm.kotlindemo

import org.junit.Test

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
}