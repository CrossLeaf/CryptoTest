package com.eton.cryptotest.ui.wallet

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eton.cryptotest.R
import com.eton.cryptotest.databinding.FragmentWalletBinding

class WalletFragment : Fragment() {

    private lateinit var walletViewModel: WalletViewModel
    private var _binding: FragmentWalletBinding? = null

    private val binding get() = _binding!!
    var walletAdapter = WalletAdapter()

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
        walletViewModel.currencyLiveData.observe(viewLifecycleOwner, {
            walletAdapter.setCurrencyList(it)
        })

        walletViewModel.totalValueLiveData.observe(viewLifecycleOwner, {
            val text = "$ ${String.format("%.2f", it)} USD"
            binding.tvBalance.text = SpannableString(text).also {
                it.setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(requireContext(), R.color.white)
                    ),
                    1, text.length - 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                it.setSpan(
                    RelativeSizeSpan(1.2f),
                    1, text.length - 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        })

        walletViewModel.statusLiveData.observe(viewLifecycleOwner, {
            when (it) {
                WalletViewModel.STATUS.LOADING -> {
                    binding.recyclerCurrencies.visibility = View.GONE
                    binding.tvStatus.let {
                        it.visibility = View.VISIBLE
                        it.text = "Loading"
                    }
                }

                WalletViewModel.STATUS.SUCCESS -> {
                    if (walletViewModel.currencies.isEmpty()) {
                        binding.recyclerCurrencies.visibility = View.GONE
                        binding.tvStatus.let {
                            it.visibility = View.VISIBLE
                            it.text = "Empty list"
                        }
                    } else {
                        binding.recyclerCurrencies.visibility = View.VISIBLE
                        binding.tvStatus.visibility = View.GONE
                    }
                }

                WalletViewModel.STATUS.FAILURE -> {
                    binding.recyclerCurrencies.visibility = View.GONE
                    binding.tvStatus.let {
                        it.visibility = View.VISIBLE
                        it.text = "Failure"
                    }
                }
            }
        })
    }

    private fun initData() {
        walletViewModel.getWallet()
    }

    private fun initView() {
        binding.recyclerCurrencies.let {
            it.layoutManager =
                LinearLayoutManager(context).apply { orientation = LinearLayoutManager.VERTICAL }
            it.adapter = walletAdapter.apply {
                setCurrencyList(walletViewModel.currencies)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}