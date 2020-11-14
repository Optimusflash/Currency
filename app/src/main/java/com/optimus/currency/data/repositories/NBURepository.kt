package com.optimus.currency.data.repositories

import com.optimus.currency.data.model.NBUCurrency
import com.optimus.currency.data.remote.NBUApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class NBURepository @Inject constructor(@Named("NBUApi") private val nbuApi: NBUApiService) {

    suspend fun loadCurrenciesFromApi(): List<NBUCurrency> {
        return withContext(Dispatchers.IO) {
            nbuApi.getCurrencies()
        }
    }
}