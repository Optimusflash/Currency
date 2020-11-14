package com.optimus.currency.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */


data class PrivatBankResponse(
    val date: String,
    val exchangeRate: List<PrivatBankCurrency>
)

data class PrivatBankCurrency(
    val currency: String?,
    val saleRate : Float?,
    val purchaseRate : Float?
)

data class NBUCurrency(
    @SerializedName("txt") val currencyUkrName: String?,
    @SerializedName("rate") val rate: Float?,
    @SerializedName("cc") val alphaName: String?,
    @SerializedName("exchangedate") val date: String?
)