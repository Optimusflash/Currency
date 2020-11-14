package com.optimus.currency.data.repositories

import com.optimus.currency.data.model.PrivatBankResponse
import com.optimus.currency.data.remote.PrivatBankApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class PrivatBankRepository @Inject constructor(@Named("PbApi")private val privatBankApi: PrivatBankApiService) {

    suspend fun loadCurrenciesFromApi(date: String): PrivatBankResponse {
        return withContext(Dispatchers.IO) {
            privatBankApi.getCurrencies(date = date)
        }
    }
}