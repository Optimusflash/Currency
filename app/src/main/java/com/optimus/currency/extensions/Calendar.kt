package com.optimus.currency.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dmitriy Chebotar on 14.11.2020.
 */

fun Calendar.formatDate(pattern: String = "dd.MM.yyyy"): String{
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return simpleDateFormat.format(this.time)
}