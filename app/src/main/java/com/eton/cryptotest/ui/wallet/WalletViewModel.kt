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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WalletViewModel : ViewModel() {
    private val repository by lazy { WalletRepository() }
    private val currencyMap = mutableMapOf<String, Currency>()

    var currencies = ArrayList<Currency>()
    val currencyLiveData = MutableLiveData<ArrayList<Currency>>()
    val totalValueLiveData = MutableLiveData<Double>()
    val statusLiveData = MutableLiveData<STATUS>()

    /**
     * get wallet data
     */
    fun getWallet() {
        viewModelScope.launch(Dispatchers.IO) {
            statusLiveData.postValue(STATUS.LOADING("Loading"))
            delay(3000)
            val currencyTask = async { getCurrency() }
            val liveRatesTask = async { getLiveRates() }
            val walletBalanceTask = async { getWalletBalance() }
            val currencyList = currencyTask.await()
            val liveRateList = liveRatesTask.await()
            val walletBalanceList = walletBalanceTask.await()
            combineData(currencyList, liveRateList, walletBalanceList)
            calculateTotalValue()
        }
    }

    /**
     * get currency data from api
     */
    private suspend fun getCurrency(): List<CurrenciesItem> {
        val apiResult = repository.getCurrencies()
        if (apiResult.ok && apiResult.currencies != null) {
            return apiResult.currencies
        } else {
            statusLiveData.postValue(STATUS.FAILURE("Failure"))
        }
        return emptyList()
    }

    /**
     * get live rates data from api
     */
    private suspend fun getLiveRates(): List<TiersItem> {
        val apiResult = repository.getLiveRates()
        if (apiResult.ok) {
            return apiResult.tiers ?: emptyList()
        } else {
            statusLiveData.postValue(STATUS.FAILURE("Failure"))
        }
        return emptyList()
    }

    /**
     * get wallet balance data from api
     */
    private suspend fun getWalletBalance(): List<WalletItem> {
        val apiResult = repository.getWalletBalance()
        if (apiResult.ok) {
            return apiResult.wallet ?: emptyList()
        } else {
            statusLiveData.postValue(STATUS.FAILURE("Failure"))
        }
        return emptyList()
    }

    /**
     * combine api data
     */
    private fun combineData(
        currencyList: List<CurrenciesItem>,
        liveRateList: List<TiersItem>,
        walletBalanceList: List<WalletItem>
    ) {
        if (statusLiveData.value == STATUS.FAILURE("Failure")) {
            return
        }
        // add currency
        for (item in currencyList) {
            val data = currencyMap[item.symbol]
            if (data == null) {
                val currency = Currency(
                    item.colorfulImageUrl,
                    item.name,
                    item.symbol
                )
                currencyMap[item.coinId] = currency
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
        statusLiveData.postValue(STATUS.SUCCESS(if (currencies.isEmpty()) "Empty list" else ""))
    }

    /**
     * Calculate wallet total amount.
     */
    private fun calculateTotalValue() {
        var totalValue = 0.0
        for (currency in currencyMap.values) {
            totalValue += currency.value
        }

        totalValueLiveData.postValue(totalValue)
    }

    sealed class STATUS {
        data class LOADING(val text: String = "") : STATUS()
        data class FAILURE(val text: String = "") : STATUS()
        data class SUCCESS(val text: String = "") : STATUS()
    }
}
