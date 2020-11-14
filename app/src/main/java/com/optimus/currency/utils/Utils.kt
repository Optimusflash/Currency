package com.optimus.currency.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dmitriy Chebotar on 14.11.2020.
 */
object Utils {

    fun formatDate(formatString: String, calendar: Calendar): String {
        return SimpleDateFormat(
                formatString,
                Locale.getDefault()
        ).format(Date(calendar.timeInMillis))
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun parseDateToMilliseconds(dateString: String?, dateFormat: String?): Long? {
        if (dateString.isNullOrEmpty()) return null
        val format = SimpleDateFormat(dateFormat, Locale.getDefault())
        return try {
            format.parse(dateString).time
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun formatDate(dateString: String?, dateFormatFrom: String?, dateFormatTo: String?): String? {
        if (dateFormatFrom.isNullOrEmpty() || dateFormatTo.isNullOrEmpty()) return null

        val dateInMilliseconds = parseDateToMilliseconds(dateString, dateFormatFrom) ?: return null

        val calendar = Calendar.getInstance().apply { timeInMillis = dateInMilliseconds }

        return formatDate(dateFormatTo, calendar)
    }


}