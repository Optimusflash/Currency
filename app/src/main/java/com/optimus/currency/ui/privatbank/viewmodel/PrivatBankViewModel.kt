package com.optimus.currency.ui.privatbank.viewmodel

import androidx.lifecycle.*
import com.optimus.currency.data.model.PrivatBankCurrency
import com.optimus.currency.data.repositories.PrivatBankRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class PrivatBankViewModel @Inject constructor(private val repository: PrivatBankRepository): ViewModel() {

    private lateinit var _currencies: LiveData<List<PrivatBankCurrency>>
    val currencies: LiveData<List<PrivatBankCurrency>>
    get() = _currencies

    init {
        getCurrencies()
    }

    private fun getCurrencies() {
        viewModelScope.launch {
            _currencies = liveData {
                emit(repository.loadCurrenciesFromApi().exchangeRate)
            }
        }
    }
}