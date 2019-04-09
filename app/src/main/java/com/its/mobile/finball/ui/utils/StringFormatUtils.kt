package com.its.mobile.finball.ui.utils

fun Int.toSpacedString(): String {
    val intStr = toString()
    val strBuilder = StringBuilder(intStr)
    for (i in 1..intStr.length / 3) {
        strBuilder.insert(strBuilder.length - i * 3 - i + 1, " ")
    }
    return strBuilder.toString()
}