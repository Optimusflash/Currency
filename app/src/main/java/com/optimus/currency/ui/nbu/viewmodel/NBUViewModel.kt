package com.optimus.currency.ui.nbu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.optimus.currency.data.model.NBUCurrency
import com.optimus.currency.data.repositories.NBURepository
import com.optimus.currency.extensions.formatDate
import com.optimus.currency.utils.Resource
import com.optimus.currency.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 14.11.2020.
 */

class NBUViewModel @Inject constructor(private val repository: NBURepository) : ViewModel() {

    private val _currenciesNBU = MutableLiveData<Resource<List<NBUCurrency>>>()
    val currenciesNBU: LiveData<Resource<List<NBUCurrency>>>
        get() = _currenciesNBU

    private var _currencyPositionIndex = SingleLiveEvent<Int>()
    val currencyPositionIndex: SingleLiveEvent<Int>
        get() = _currencyPositionIndex

    private val _calendarDate = MutableLiveData<String>()
    val calendarDate: LiveData<String>
        get() = _calendarDate

    init {
        Calendar.getInstance().formatDate("yyyyMMdd").apply { _calendarDate.value = this }
        getCurrencies()
    }

    private fun getCurrencies() {
        viewModelScope.launch {
            _currenciesNBU.value = Resource.Loading()
            val currencies = repository.loadCurrenciesFromApi(_calendarDate.value ?: return@launch)
            repository.nbuItems = currencies
            _currenciesNBU.value = currencies
        }
    }

    fun handleDate(dateInMills: Long) {
        _calendarDate.value = dateInMills.formatDate("yyyyMMdd")
        getCurrencies()
    }

    fun handleClick(currencyCode: String) {
        val nbuCurrencies = repository.nbuItems
        val nbuCurrency = nbuCurrencies?.data?.firstOrNull {
            it.alphaName == currencyCode
        }
        val index = nbuCurrencies?.data?.indexOf(nbuCurrency)
        if (index == -1) return

        updateSelectedItem(nbuCurrencies, index)

        _currenciesNBU.value = repository.nbuItems
        _currencyPositionIndex.value = index
    }

    private fun updateSelectedItem(nbuCurrencies: Resource<List<NBUCurrency>>?, selectCurrencyIndex: Int?) {
        nbuCurrencies?.data?.forEachIndexed { index, nbuCurrency ->
            nbuCurrency.isSelected = selectCurrencyIndex == index
        }
        repository.nbuItems = nbuCurrencies
    }
}