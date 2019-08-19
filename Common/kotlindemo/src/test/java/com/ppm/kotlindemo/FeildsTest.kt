package com.ppm.kotlindemo

import android.location.Address
import org.junit.Test
import java.util.*
import java.util.jar.Attributes

fun copyAddress(address: Address): Address {
    val result = Address(Locale.CHINA)
    result.countryName = ""
    return result
}

var allByDefault = 0


class MyClass() {

    val isEmpty: Boolean get() = this.size() == 0

    fun size(): Int {
        return 1
    }

    var stringRepresentation: String
        get() = this.toString()
        set(value) {
            setDataFromString(value)
        }

    fun setDataFromString(value: String) {
    }

    var setterVisibility: String = "abc"
        private set

    var setterWithAnnotation: Any? = null

    interface MyInterface {
        val prop: Int

        fun bar()
        fun foo() {

        }

        val propertyWithImplemention: String
            get() = "foo"

        fun foo1() {
            print(prop)
        }
    }

    class Child : MyInterface {
        override fun bar() {
        }

        override val prop: Int
            get() = 123
    }

    interface Named {
        val name: String
    }

    interface Person : Named {
        val firstName: String
        val lastName: String

        override val name: String
            get() = "$firstName $lastName"
    }

    data class Employee(
        override val firstName: String, override val lastName: String, val position: String
    ) : Person

    internal val baz = 5

    open class Outer {
        private val a = 1
        protected open val b = 2
        internal val c = 3
        val d = 4

        protected class Nested {
            public val e: Int = 5
        }
    }

    class Subclass : Outer() {
        override val b = 5
    }

    class Unrelated(o: Outer) {
    }

    class C private constructor(a: Int) {

    }

    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }


    fun listTest() {
        val list = mutableListOf(1, 2, 3)
        list.swap(0, 2)
    }

    open class S

    class D : S()

    fun S.foo() = "s"

    fun D.foo() = "d"

    fun printFoo(s: S) {
        println(s.foo())
    }

    @Test
    fun testEx() {
        printFoo(D())
    }

    fun Any?.toString(): String {
        if (this == null) return "null"
        return toString()
    }

    class MyClass1 {
        companion object {

        }

    }

    fun MyClass1.Companion.foo() {

    }

    fun t() {
        MyClass1.foo()
    }

    open class classD {}

    class classD1 : classD() {}

    open class ClassC {
        open fun classD.foo() {
            println("D.foo in C")
        }

        open fun classD1.foo() {
            println("D1.foo in C")
        }

        fun caller(d: classD) {
            d.foo()
        }
    }

    class classC1 : ClassC() {
        override fun classD.foo() {
        }

        override fun classD1.foo() {
        }
    }

    @Test
    fun main() {
        ClassC().caller(classD())
        classC1().caller(classD())
        ClassC().caller(classD1())
    }

    data class User(val name: String, val age: Int)


}


