package com.eton.cryptotest.repository

import com.eton.cryptotest.model.Currencies
import com.eton.cryptotest.model.LiveRates
import com.eton.cryptotest.model.WalletBalance
import com.eton.cryptotest.source.ApiSource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WalletRepository {
    suspend fun getCurrencies(): Currencies {
        return withContext(Dispatchers.IO) {
            Gson().fromJson(ApiSource.currencies, object : TypeToken<Currencies>() {}.type)
        }
    }

    suspend fun getLiveRates(): LiveRates {
        return withContext(Dispatchers.IO){
            Gson().fromJson(ApiSource.liveRates, object : TypeToken<LiveRates>() {}.type)
        }
    }

    suspend fun getWalletBalance(): WalletBalance {
        return withContext(Dispatchers.IO){
            Gson().fromJson(ApiSource.walletBalance, object : TypeToken<WalletBalance>() {}.type)
        }
    }

}