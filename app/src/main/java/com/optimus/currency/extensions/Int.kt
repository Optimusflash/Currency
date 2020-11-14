package com.optimus.currency.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

/**
 * Created by Dmitriy Chebotar on 14.11.2020.
 */

fun Float.toDecimalFormat(): String {
    val formatSymbols = DecimalFormatSymbols(Locale.getDefault()).apply {
        decimalSeparator = '.'
    }
    return DecimalFormat("###,##0.000", formatSymbols).format(this)
}