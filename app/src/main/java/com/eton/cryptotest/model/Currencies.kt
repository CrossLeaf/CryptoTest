package com.eton.cryptotest.model

data class Currencies(val ok: Boolean = false,
                      val total: Int = 0,
                      val currencies: List<CurrenciesItem>?)

data class CurrenciesItem(val depositAddressTagName: String = "",
                          val symbol: String = "",
                          val code: String = "",
                          val coinId: String = "",
                          val grayImageUrl: String = "",
                          val withdrawalEta: List<String>?,
                          val blockchainSymbol: String = "",
                          val contractAddress: String = "",
                          val hasDepositAddressTag: Boolean = false,
                          val numConfirmationRequired: Int = 0,
                          val minBalance: Int = 0,
                          val tokenDecimal: Int = 0,
                          val tradingSymbol: String = "",
                          val displayDecimal: Int = 0,
                          val gasLimit: Int = 0,
                          val depositAddressTagType: String = "",
                          val supportsLegacyAddress: Boolean = false,
                          val tokenDecimalValue: String = "",
                          val colorfulImageUrl: String = "",
                          val isErc: Boolean = false,
                          val name: String = "",
                          val explorer: String = "")