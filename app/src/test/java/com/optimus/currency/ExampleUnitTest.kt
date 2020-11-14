package com.optimus.currency

import com.optimus.currency.extensions.formatDate
import com.optimus.currency.extensions.toDecimalFormat
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_decimal() {
        val a = 33.0f
        val b = 12345.123f
        val c = 0.2299f

        println(a.toDecimalFormat())
        println(b.toDecimalFormat())
        println(c.toDecimalFormat())
    }

    @Test
    fun test_date_format() {
        val date = "20201114"
        println(date .formatDate())
    }
}