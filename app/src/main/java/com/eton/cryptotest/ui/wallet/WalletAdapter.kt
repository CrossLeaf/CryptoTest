package com.eton.cryptotest.ui.wallet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eton.cryptotest.R
import com.eton.cryptotest.model.Currency

class WalletAdapter : RecyclerView.Adapter<WalletAdapter.WalletViewHolder>() {
    lateinit var currencies: ArrayList<Currency>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        return WalletViewHolder(view)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    class WalletViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCurrency = itemView.findViewById<ImageView>(R.id.img_currency)
        val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        val tvAmount = itemView.findViewById<TextView>(R.id.tv_amount)
        val tvValue = itemView.findViewById<TextView>(R.id.tv_value)
    }
}