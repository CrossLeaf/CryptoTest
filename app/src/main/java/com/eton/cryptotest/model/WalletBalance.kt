package com.eton.cryptotest.model

data class WalletBalance(val ok: Boolean = false,
                         val warning: String = "",
                         val wallet: List<WalletItem>?)


data class WalletItem(val currency: String = "",
                      val amount: Int = 0)


