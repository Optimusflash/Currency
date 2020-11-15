package com.optimus.currency.data.repositories

import com.optimus.currency.data.model.NBUCurrency
import com.optimus.currency.data.remote.NBUApiService
import com.optimus.currency.utils.Resource
import com.optimus.currency.utils.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class NBURepository @Inject constructor(
    @Named("NBUApi") private val nbuApi: NBUApiService,
    @Named("NBUResponseHandler") private val responseHandler: ResponseHandler
) {
    var nbuItems: Resource<List<NBUCurrency>>? = null

    suspend fun loadCurrenciesFromApi(date: String): Resource<List<NBUCurrency>> {
        return withContext(Dispatchers.IO) {
            try {
                val currencies = nbuApi.getCurrencies(date = date)
                responseHandler.handleSuccess(currencies)
            } catch (e: Exception) {
                responseHandler.handleException(e)
            }
        }
    }
}