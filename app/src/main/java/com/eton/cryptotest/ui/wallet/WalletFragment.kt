package com.eton.cryptotest.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eton.cryptotest.databinding.FragmentWalletBinding

class WalletFragment : Fragment() {

    private lateinit var walletViewModel: WalletViewModel
    private var _binding: FragmentWalletBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        walletViewModel =
            ViewModelProvider(this).get(WalletViewModel::class.java)

        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initData()
        initView()
        initLiveData()
        return root
    }

    private fun initLiveData() {

    }

    private fun initData() {
        walletViewModel.getCurrency()
    }

    private fun initView() {
        binding.recyclerCurrencies.let {
            it.layoutManager =
                LinearLayoutManager(context).apply { orientation = LinearLayoutManager.VERTICAL }
            it.adapter = WalletAdapter().apply {
                currencies = walletViewModel.currencies
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}