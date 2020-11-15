package com.optimus.currency.utils

/**
 * Created by Dmitriy Chebotar on 15.11.2020.
 */

sealed class Resource <T>(
    var data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}