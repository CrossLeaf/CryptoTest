package com.eton.cryptotest.ui.wallet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eton.cryptotest.model.CurrenciesItem
import com.eton.cryptotest.model.Currency
import com.eton.cryptotest.model.TiersItem
import com.eton.cryptotest.model.WalletItem
import com.eton.cryptotest.repository.WalletRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WalletViewModel : ViewModel() {
    private val repository by lazy { WalletRepository() }

    var currencies = ArrayList<Currency>()
    val currencyLiveData = MutableLiveData<ArrayList<Currency>>()
    val currencyMap = mutableMapOf<String, Currency>()

    fun getWallet() {
        viewModelScope.launch(Dispatchers.IO) {
            val currencyTask = async { getCurrency() }
            val liveRatesTask = async { getLiveRates() }
            val walletBalanceTask = async { getWalletBalance() }
            val currencyList = currencyTask.await()
            val liveRateList = liveRatesTask.await()
            val walletBalanceList = walletBalanceTask.await()
            combineData(currencyList, liveRateList, walletBalanceList)
        }
    }

    suspend fun getCurrency(): List<CurrenciesItem> {
        val apiResult = repository.getCurrencies()
        if (apiResult.ok && apiResult.currencies != null) {

            return apiResult.currencies
        } else {
            // TODO: 2021/9/1 show error msg
        }
        return emptyList()
    }

    suspend fun getLiveRates(): List<TiersItem> {
        val apiResult = repository.getLiveRates()
        if (apiResult.ok) {
            return apiResult.tiers ?: emptyList()
        } else {
            // TODO: 2021/9/1 show error msg
        }
        return emptyList()
    }

    suspend fun getWalletBalance(): List<WalletItem> {
        val apiResult = repository.getWalletBalance()
        if (apiResult.ok) {
            return apiResult.wallet ?: emptyList()
        } else {
            // TODO: 2021/9/1 show error msg
        }
        return emptyList()
    }

    fun combineData(
        currencyList: List<CurrenciesItem>,
        liveRateList: List<TiersItem>,
        walletBalanceList: List<WalletItem>
    ) {
        // add currency
        for (item in currencyList) {
            val data = currencyMap[item.symbol]
            if (data == null) {
                val currency = Currency(
                    item.colorfulImageUrl,
                    item.name,
                    item.symbol
                )
                currencyMap.set(item.coinId, currency)
            } else {
                data.picture = item.colorfulImageUrl
                data.name = item.name
            }
        }

        // add amount
        for (item in walletBalanceList) {
            val data = currencyMap[item.currency]
            if (data != null) {
                data.amount = item.amount
            }
        }

        // add value
        for (item in liveRateList) {
            val data = currencyMap[item.fromCurrency]
            if (data != null) {
                val rate = item.rates?.get(0)?.rate
                if (rate != null)
                    data.value = rate * data.amount
            }
        }

        currencies.addAll(currencyMap.values)
        currencyLiveData.postValue(currencies)
    }
}