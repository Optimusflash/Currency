package com.optimus.currency.data.model

/**
 * Created by Dmitriy Chebotar on 14.11.2020.
 */
data class PrivatBankCurrency(
        var currency: String?,
        val saleRate : Float?,
        val purchaseRate : Float?
)