package com.optimus.currency.data.remote

import com.optimus.currency.data.model.NBUCurrency
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
interface NBUApiService {
    @GET("NBUStatService/v1/statdirectory/exchangenew?json")
    suspend fun getCurrencies(
       // @Query("json") json: String? = "",
        @Query("date") date: String
    ) : List<NBUCurrency>
}