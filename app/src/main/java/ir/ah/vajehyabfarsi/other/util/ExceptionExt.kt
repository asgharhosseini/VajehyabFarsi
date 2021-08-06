package ir.ah.vajehyabfarsi.other.util

import ir.ah.vajehyabfarsi.BuildConfig


fun Exception.print() {
    if (BuildConfig.DEBUG)
        printStackTrace()
}

fun Throwable.print() {
    if (BuildConfig.DEBUG)
        printStackTrace()
}

