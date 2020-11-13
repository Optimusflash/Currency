package com.optimus.currency.data.repositories

import com.optimus.currency.data.model.PrivatBankResponse
import com.optimus.currency.data.remote.PrivatBankApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class PrivatBankRepository @Inject constructor(private val privatBankApi: PrivatBankApiService) {

    suspend fun loadCurrenciesFromApi(): PrivatBankResponse{
        return withContext(Dispatchers.IO){
            privatBankApi.getCurrencies()
        }
    }
}