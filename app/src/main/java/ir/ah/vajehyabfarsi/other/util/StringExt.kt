package ir.ah.vajehyabfarsi.other.util

import java.text.SimpleDateFormat
import java.util.*


fun dateToName(date: Date):String{
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return dateFormat.format(date.toLocaleString())
}