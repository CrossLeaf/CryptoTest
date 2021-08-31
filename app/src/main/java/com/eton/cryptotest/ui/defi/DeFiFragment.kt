package com.eton.cryptotest.ui.defi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eton.cryptotest.databinding.FragmentDefiBinding

class DeFiFragment : Fragment() {

    private lateinit var deFiViewModel: DeFiViewModel
    private var _binding: FragmentDefiBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deFiViewModel =
            ViewModelProvider(this).get(DeFiViewModel::class.java)

        _binding = FragmentDefiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDeFi
        deFiViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}