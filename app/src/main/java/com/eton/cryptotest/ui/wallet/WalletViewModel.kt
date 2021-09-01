package com.eton.cryptotest.ui.wallet

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eton.cryptotest.model.Currency
import com.eton.cryptotest.repository.WalletRepository
import kotlinx.coroutines.launch

class WalletViewModel : ViewModel() {
    private val repository by lazy { WalletRepository() }

    var currencies = ArrayList<Currency>()
    val currencyLiveData = MutableLiveData<ArrayList<Currency>>()

    fun getCurrency() {
        viewModelScope.launch {
            Log.d("TAG", "getCurrency: ${repository.getCurrencies()}")
            val apiResult = repository.getCurrencies()
            if (apiResult.ok) {
                for (currency in apiResult.currencies!!) {
                    if (currency.symbol.contentEquals("BTC") ||
                        currency.symbol.contentEquals("ETH")||
                        currency.symbol.contentEquals("CRO"))
                    currencies.add(Currency(currency.colorfulImageUrl, currency.name, "10", "20"))
                    currencyLiveData.value = currencies
                }
            } else {
                // TODO: 2021/9/1 show error msg
            }
        }
    }
}