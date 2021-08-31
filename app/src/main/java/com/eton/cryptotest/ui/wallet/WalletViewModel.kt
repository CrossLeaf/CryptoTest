package com.eton.cryptotest.ui.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eton.cryptotest.model.Currency

class WalletViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is wallet Fragment"
    }
    val text: LiveData<String> = _text

    var currencies = ArrayList<Currency>()
    val currencyLiveData = MutableLiveData<ArrayList<Currency>>()

    fun getCurrency() {
        currencies.add(Currency("1","1","1","1"))
        currencies.add(Currency("1","1","1","1"))
        currencies.add(Currency("1","1","1","1"))
        currencies.add(Currency("1","1","1","1"))
        currencyLiveData.value = currencies
    }
}