package com.optimus.currency.ui.nbu.viewmodel

import androidx.lifecycle.*
import com.optimus.currency.data.model.NBUCurrency
import com.optimus.currency.data.repositories.NBURepository
import com.optimus.currency.extensions.formatDate
import com.optimus.currency.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 14.11.2020.
 */

class NBUViewModel @Inject constructor(private val repository: NBURepository) : ViewModel() {

    private lateinit var _currenciesNBU: LiveData<List<NBUCurrency>>
    val currenciesNBU: LiveData<List<NBUCurrency>>
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
        _currenciesNBU = Transformations.switchMap(_calendarDate) {
            liveData {
                emit(repository.loadCurrenciesFromApi(it))
            }
        }
    }

    fun handleDate(dateInMills: Long) {
        _calendarDate.value = dateInMills.formatDate("yyyyMMdd")
    }

    fun handleClick(currencyCode: String) {
        val nbuCurrencies = _currenciesNBU.value
        val nbuCurrency = nbuCurrencies?.firstOrNull {
            it.alphaName.startsWith(currencyCode, true)
        }
        val index = nbuCurrencies?.indexOf(nbuCurrency)
        if (index != -1){
            setSelectedItem(nbuCurrencies, index)
        }
        _currencyPositionIndex.value = index
    }

    private fun setSelectedItem(nbuCurrencies: List<NBUCurrency>?, index: Int?){
        if (nbuCurrencies == null || index == null) return
        val selectedCurrency = nbuCurrencies.firstOrNull {
            it.isSelected
        }
        if (selectedCurrency != null ){
            selectedCurrency.isSelected = false
            nbuCurrencies[index].isSelected = true
        } else {
            nbuCurrencies[index].isSelected = true
        }
    }
}