package com.optimus.currency.data.model

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */


data class PrivatBankResponse(
    val date: String,
    val exchangeRate: List<PrivatBankCurrency>
)

