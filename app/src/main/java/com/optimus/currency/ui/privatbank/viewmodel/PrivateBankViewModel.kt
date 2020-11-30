package com.optimus.currency.ui.privatbank.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.optimus.currency.data.model.PrivatBankCurrency
import com.optimus.currency.data.repositories.PrivatBankRepository
import com.optimus.currency.extensions.formatDate
import com.optimus.currency.utils.Resource
import java.util.*
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class PrivateBankViewModel @ViewModelInject constructor(private val privatBankRepository: PrivatBankRepository) :
    ViewModel() {

    private lateinit var _currencies: LiveData<Resource<List<PrivatBankCurrency>>>
    val currencies: LiveData<Resource<List<PrivatBankCurrency>>>
        get() = _currencies

    private val _calendarDate = MutableLiveData<String>()
    val calendarDate: LiveData<String>
        get() = _calendarDate

    init {
        Calendar.getInstance().formatDate().apply { _calendarDate.value = this }
        getCurrencies()
    }

    private fun getCurrencies() {
        _currencies = Transformations.switchMap(_calendarDate) {
            liveData {
                emit(Resource.Loading())
                val exchangeRateList = privatBankRepository.loadCurrenciesFromApi(it)
                emit(filterIncorrectData(exchangeRateList))
            }
        }
    }

    private fun filterIncorrectData(exchangeRateList: Resource<List<PrivatBankCurrency>>): Resource<List<PrivatBankCurrency>>? {
        val data = exchangeRateList.data
        val list = data?.filterNot {
            it.currency == null || it.purchaseRate == null || it.saleRate == null
        }
        //mismatch of the alpha code of the currency from the api of PrivatBank to the Polish zloty. Made the alpha code like in the NBU
        list?.find { it.currency == "PLZ" }?.currency = "PLN"
        return Resource.Success(list)
    }

    fun handleDate(dateInMills: Long) {
        _calendarDate.value = dateInMills.formatDate()
    }
}