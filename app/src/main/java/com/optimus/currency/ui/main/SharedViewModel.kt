package com.optimus.currency.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.optimus.currency.utils.SingleLiveEvent
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class SharedViewModel @ViewModelInject constructor() : ViewModel() {
    private val _clickedCurrencyCode = SingleLiveEvent<String>()
    val clickedCurrencyCode: SingleLiveEvent<String>
        get() = _clickedCurrencyCode

    fun handleCurrencyClick(currencyCode: String?) {
        currencyCode ?: return
        _clickedCurrencyCode.value = currencyCode
    }
}