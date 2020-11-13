package com.optimus.currency.data.model

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */


data class PrivatBankResponse(
    val date: String,
    val exchangeRate: List<PrivatBankCurrency>
)

data class PrivatBankCurrency(
    val baseCurrency: String,
    val currency: String,
    val saleRateNB: Float?,
    val purchaseRateNB: Float?,
    val saleRate : Float?,
    val purchaseRate : Float?
)