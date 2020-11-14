package com.optimus.currency.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dmitriy Chebotar on 14.11.2020.
 */

fun Long.formatDate(pattern: String = "dd.MM.yyyy"): String{
    val date = Date(this)
    return SimpleDateFormat(pattern, Locale.getDefault()).format(date)
}