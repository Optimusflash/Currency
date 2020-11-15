package com.optimus.currency.utils

import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException

/**
 * Created by Dmitriy Chebotar on 15.11.2020.
 */
class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.Success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.Error(getErrorMessage(e.code()))
            is SocketTimeoutException -> Resource.Error(getErrorMessage(-1))
            else -> Resource.Error(getErrorMessage(Int.MAX_VALUE))
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            -1 -> "Timeout. Please try again later"
            504 -> "(504) Gateway timeout. Please try again later"
            else -> "Something was wrong. Please try again later"
        }
    }
}