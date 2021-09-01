package com.eton.cryptotest.model

data class LiveRates(val ok: Boolean = false,
                     val warning: String = "",
                     val tiers: List<TiersItem>?)


data class TiersItem(val fromCurrency: String = "",
                     val toCurrency: String = "",
                     val rates: List<RatesItem>?,
                     val timeStamp: Int = 0)


data class RatesItem(val amount: String = "",
                     val rate: String = "")


