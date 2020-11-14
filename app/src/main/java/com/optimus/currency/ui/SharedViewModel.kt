package com.optimus.currency.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.optimus.currency.utils.SingleLiveEvent
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _clickedCurrencyCode = SingleLiveEvent<String>()
    val clickedCurrencyCode: SingleLiveEvent<String>
        get() = _clickedCurrencyCode

    fun handleCurrencyClick(currencyCode: String?) {
        Log.e("M_SharedViewModel", "handleCurrencyClick $currencyCode")
        currencyCode ?: return
        _clickedCurrencyCode.value = currencyCode
    }
}