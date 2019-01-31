package com.ppm.kotlindemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun sum(a: Int, b: Int) = a + b
    }


    public fun sum1(a: Int, b: Int): Int = a + b

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun printSum(a: Int, b: Int): Unit {
        print(a + b)
    }


}
