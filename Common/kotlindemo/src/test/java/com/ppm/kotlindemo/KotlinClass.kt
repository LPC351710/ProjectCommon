package com.ppm.kotlindemo

import com.ppm.ppcomon.widget.addresspicker.picker.LinkagePicker

open class Base(val name: String) {

    init {
        println("Initializing Base")
    }

    open val size: Int = name.length.also { println("Initializing size in Base: $it") }

    class Derived(name: String, val lastName: String) :
        Base(name.capitalize().also { println("Argument for Base: $it") }) {

        init {
            println("Initializing Derived")
        }

        override val size: Int = (super.size + lastName.length).also {
            println("Initializing size Derived: $it")
        }
    }

    open class Foo {
        open fun f() {
            println("Foo.fun()")
        }

        open val x: Int get() = 1
    }

    class Bar : Foo() {
        override fun f() {
            super.f()

            println("Bar.f()")
        }

        override val x: Int
            get() = super.x + 1
    }

    class Bar1 : Foo() {
        override fun f() {
            super.f()
        }

        override val x: Int
            get() = 0

        inner class Baz {
            fun g() {
                super@Bar1.f()
                println(super@Bar1.x)
            }
        }
    }


    open class A {
        open fun f() {
            print("A")
        }

        fun a() {
            print("a")
        }
    }

    open class BaseA(x: Int) {
        public open val y: Int = x
    }

    interface B {
        fun s()
    }

    val ad: BaseA = object : BaseA(1), B {

        override fun s() {
        }

        override val y = 1
    }

    fun foo() {
        val adHoc = object {
            var x: Int = 0
            var y: Int = 0
        }
        print(adHoc.x + adHoc.y)
    }

    class C {
        private fun foo() = object {
            val x: String = "x"
        }

        fun publicFoo() = object {
            val x: String = "x"
        }

        fun bar() {
            val x1 = foo().x
//            val x2 = publicFoo().x
        }
    }

    object DataProviderManager {
        fun registerDataProvider(provider: LinkagePicker.DataProvider) {

        }

        val allDataProvider: Collection<LinkagePicker.DataProvider>? = null

        val instance = MyClass.create()
    }


    class MyClass {
        companion object Factory {
            fun create(): MyClass = MyClass()
        }
    }

    class MyClass1 {
        companion object {}
    }

    val x = MyClass1.Companion

    class MyClass2 {
        companion object Named {}
    }

    val x1 = MyClass2

    class MyClass3 {
        companion object {}
    }

    val y = MyClass3

    interface Factory<T> {
        fun create(): T
    }

    class MyClass11 {
        companion object : Factory<MyClass11> {
            override fun create(): MyClass11 = MyClass11()
        }
    }

    val f: Factory<MyClass11> = MyClass11
}
