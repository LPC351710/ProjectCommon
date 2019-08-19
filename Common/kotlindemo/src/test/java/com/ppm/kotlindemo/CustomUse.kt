package com.ppm.kotlindemo

import java.io.File

data class Customer(val name: String, val email: String)

fun foo(a: Int = 0, b: String = "") {

    val list: MutableList<Int> = ArrayList()

    val positives = list.filter { x -> x > 0 }

    val positives1 = list.filter { it > 0 }

    val maps: MutableMap<String, String> = HashMap()

    for ((key, value) in maps) {
    }

    val onlyReadList = listOf("a", "b", "c")

    val onlyReadMap = mapOf("a" to 1, "b" to 2)

    val p: String by lazy {
        ""
    }

    fun String.spaceToCamelCase() {
    }
    "Covert this to camelcase".spaceToCamelCase()

    val files = File("Test").listFiles()
    println(files?.size)

    val emails = ""
    val mainEmail = emails.firstOrNull() ?: ""

    val values =""
}

//single instance
object Rescourse {

}


