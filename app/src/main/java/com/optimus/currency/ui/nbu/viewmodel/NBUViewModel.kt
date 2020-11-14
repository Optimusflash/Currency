package com.optimus.currency.ui.nbu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.optimus.currency.data.model.NBUCurrency
import com.optimus.currency.data.repositories.NBURepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 14.11.2020.
 */

class NBUViewModel @Inject constructor(private val repository: NBURepository) : ViewModel() {

    private lateinit var _currenciesNBU: LiveData<List<NBUCurrency>>
    val currenciesNBU: LiveData<List<NBUCurrency>>
        get() = _currenciesNBU

    init {
        viewModelScope.launch {
            _currenciesNBU = liveData {
                emit(repository.loadCurrenciesFromApi())
            }
        }
    }
}