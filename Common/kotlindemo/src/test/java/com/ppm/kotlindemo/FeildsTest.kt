package com.ppm.kotlindemo

import android.location.Address
import java.util.*

fun copyAddress(address: Address): Address {
    val result = Address(Locale.CHINA)
    result.countryName = ""
    return result
}

var allByDefault = 0

