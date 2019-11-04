package com.chatapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun toSimpleString(currentTime: Long) : String {
    val date = Date(currentTime)
    val format = SimpleDateFormat("dd/MM/yyy")
    return format.format(date)
}