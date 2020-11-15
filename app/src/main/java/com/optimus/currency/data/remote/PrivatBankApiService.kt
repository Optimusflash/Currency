package com.optimus.currency.data.remote

import com.optimus.currency.data.model.PrivatBankResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
interface PrivatBankApiService {
    @GET("p24api/exchange_rates")
    suspend fun getCurrencies(
        @QueryName(encoded = true) json: String = "json",
        @Query("date") date: String
    ) : PrivatBankResponse
}