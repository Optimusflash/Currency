package com.optimus.currency.ui.privatbank.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.optimus.currency.data.model.PrivatBankCurrency
import com.optimus.currency.data.repositories.PrivatBankRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class PrivateBankViewModel @Inject constructor(private val privatBankRepository: PrivatBankRepository) :
    ViewModel() {

    private lateinit var _currencies: LiveData<List<PrivatBankCurrency>>
    val currencies: LiveData<List<PrivatBankCurrency>>
        get() = _currencies

    init {
        getCurrencies()
    }

    private fun getCurrencies() {
        viewModelScope.launch {
            _currencies = liveData {
                val exchangeRateList = privatBankRepository.loadCurrenciesFromApi().exchangeRate
                emit(filterIncorrectData(exchangeRateList))
            }
        }
    }

    private fun filterIncorrectData(exchangeRateList: List<PrivatBankCurrency>): List<PrivatBankCurrency> {
        return exchangeRateList.filterNot {
            it.currency == null || it.purchaseRate == null || it.saleRate == null
        }
    }
}