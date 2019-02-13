package com.ppm.kotlindemo


//主构造函数
class TestClass constructor(name: String) {

    var url: String = "http://www.baidu.com"

    var country: String = "CN"

    var siteName = name


    init {
        print("初始化网站名：${name}")
    }

    fun printTest() {
        print("this is class method")
    }

    fun main() {
        val test = TestClass("测试")
        println(test.country)
        println(test.url)
        println(test.siteName)

        test.printTest()
    }
}

class Person {
    constructor(parent: Person) {
    }
}

class Class1(var name: String) {
    constructor(name: String, age: Int) : this(name) {

    }
}