package com.eton.cryptotest.model

import com.google.gson.annotations.SerializedName

data class LiveRates(
    val ok: Boolean = false,
    val warning: String = "",
    val tiers: List<TiersItem>?
)


data class TiersItem(
    @SerializedName("from_currency")
    val fromCurrency: String = "",
    @SerializedName("to_currency")
    val toCurrency: String = "",
    val rates: List<RatesItem>?,
    @SerializedName("time_stamp")
    val timeStamp: Int = 0
)


data class RatesItem(
    val amount: String = "",
    val rate: Double = 0.0
)


