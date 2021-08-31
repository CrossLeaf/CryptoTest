package com.eton.cryptotest.ui.defi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeFiViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is DeFi Fragment"
    }
    val text: LiveData<String> = _text
}