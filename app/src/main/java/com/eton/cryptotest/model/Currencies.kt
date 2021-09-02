package com.eton.cryptotest.model


import com.google.gson.annotations.SerializedName

data class CurrenciesItem(
    @SerializedName("deposit_address_tag_name")
    val depositAddressTagName: String = "",
    @SerializedName("symbol")
    val symbol: String = "",
    @SerializedName("code")
    val code: String = "",
    @SerializedName("coin_id")
    val coinId: String = "",
    @SerializedName("gray_image_url")
    val grayImageUrl: String = "",
    @SerializedName("withdrawal_eta")
    val withdrawalEta: List<String>?,
    @SerializedName("blockchain_symbol")
    val blockchainSymbol: String = "",
    @SerializedName("contract_address")
    val contractAddress: String = "",
    @SerializedName("has_deposit_address_tag")
    val hasDepositAddressTag: Boolean = false,
    @SerializedName("num_confirmation_required")
    val numConfirmationRequired: Int = 0,
    @SerializedName("min_balance")
    val minBalance: Int = 0,
    @SerializedName("token_decimal")
    val tokenDecimal: Int = 0,
    @SerializedName("trading_symbol")
    val tradingSymbol: String = "",
    @SerializedName("display_decimal")
    val displayDecimal: Int = 0,
    @SerializedName("gas_limit")
    val gasLimit: Int = 0,
    @SerializedName("deposit_address_tag_type")
    val depositAddressTagType: String = "",
    @SerializedName("supports_legacy_address")
    val supportsLegacyAddress: Boolean = false,
    @SerializedName("token_decimal_value")
    val tokenDecimalValue: String = "",
    @SerializedName("colorful_image_url")
    val colorfulImageUrl: String = "",
    @SerializedName("is_erc20")
    val isErc: Boolean = false,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("explorer")
    val explorer: String = ""
)


data class Currencies(
    @SerializedName("total")
    val total: Int = 0,
    @SerializedName("ok")
    val ok: Boolean = false,
    @SerializedName("currencies")
    val currencies: List<CurrenciesItem>?
)


