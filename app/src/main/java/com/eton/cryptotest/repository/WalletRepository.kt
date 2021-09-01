package com.eton.cryptotest.repository

import com.eton.cryptotest.model.Currencies
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

}