package com.optimus.currency.ui.privatbank.viewmodel

import androidx.lifecycle.*
import com.optimus.currency.data.model.PrivatBankCurrency
import com.optimus.currency.data.repositories.PrivatBankRepository
import com.optimus.currency.extensions.formatDate
import java.util.*
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class PrivateBankViewModel @Inject constructor(private val privatBankRepository: PrivatBankRepository) :
    ViewModel() {

    private lateinit var _currencies: LiveData<List<PrivatBankCurrency>>
    val currencies: LiveData<List<PrivatBankCurrency>>
        get() = _currencies

    private val _calendarDate = MutableLiveData<String>()
    val calendarDate: LiveData<String>
        get() = _calendarDate

    init {
        Calendar.getInstance().formatDate().apply {_calendarDate.value = this}
        getCurrencies()
    }

    private fun getCurrencies() {
        _currencies = Transformations.switchMap(_calendarDate) {
            liveData {
                val exchangeRateList = privatBankRepository.loadCurrenciesFromApi(it).exchangeRate
                emit(filterIncorrectData(exchangeRateList))
            }
        }
    }

    private fun filterIncorrectData(exchangeRateList: List<PrivatBankCurrency>): List<PrivatBankCurrency> {
        return exchangeRateList.filterNot {
            it.currency == null || it.purchaseRate == null || it.saleRate == null
        }
    }

    fun handleDate(dateInMills: Long) {
        _calendarDate.value = dateInMills.formatDate()
    }
}