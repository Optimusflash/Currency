package com.optimus.currency.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dmitriy Chebotar on 14.11.2020.
 */
data class NBUCurrency(
        @SerializedName("txt") val currencyUkrName: String?,
        @SerializedName("rate") val rate: Float?,
        @SerializedName("cc") val alphaName: String,
        @SerializedName("exchangedate") val date: String?,
        var isSelected: Boolean = false
)