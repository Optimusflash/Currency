package com.optimus.currency.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dmitriy Chebotar on 14.11.2020.
 */

fun String.formatDate(pattern: String = "dd.MM.yyyy"): String? {
    val incomingPattern = "yyyyMMdd"
    val date = SimpleDateFormat(incomingPattern, Locale.getDefault()).parse(this)
    return if (date != null){
        SimpleDateFormat(pattern, Locale.getDefault()).format(date)
    } else {
        null
    }
}