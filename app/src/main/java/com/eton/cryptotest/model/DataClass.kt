package com.eton.cryptotest.model

data class Currency(
    var picture: String?,
    var name: String?,
    var symbol: String?,
    var amount: Double = 0.0,
    var value: Double = 0.0
)