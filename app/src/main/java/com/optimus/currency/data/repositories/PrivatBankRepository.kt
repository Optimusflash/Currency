package com.optimus.currency.data.repositories

import android.util.Log
import com.optimus.currency.data.model.PrivatBankCurrency
import com.optimus.currency.data.remote.PrivatBankApiService
import com.optimus.currency.utils.Resource
import com.optimus.currency.utils.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class PrivatBankRepository @Inject constructor(
    @Named("PbApi") private val privatBankApi: PrivatBankApiService,
    @Named("PbResponseHandler") private val responseHandler: ResponseHandler
) {

    suspend fun loadCurrenciesFromApi(date: String): Resource<List<PrivatBankCurrency>> {
        return withContext(Dispatchers.IO) {
            try {
                val currencies = privatBankApi.getCurrencies(date = date).exchangeRate
                responseHandler.handleSuccess(currencies)
            } catch (e: Exception) {
                responseHandler.handleException(e)
            }
        }
    }
}