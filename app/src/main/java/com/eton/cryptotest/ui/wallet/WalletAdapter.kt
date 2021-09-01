package com.eton.cryptotest.ui.wallet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eton.cryptotest.R
import com.eton.cryptotest.model.Currency

class WalletAdapter : RecyclerView.Adapter<WalletAdapter.WalletViewHolder>() {
    var currencies = ArrayList<Currency>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        return WalletViewHolder(view)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        currencies[position].run {
            Glide.with(context)
                .load(picture)
                .into(holder.imgCurrency)
            holder.tvName.text = name
            holder.tvAmount.text = amount
            holder.tvValue.text = value
        }
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    fun setCurrencyList(currencyList: ArrayList<Currency>) {
        currencies.clear()
        currencies.addAll(currencyList)
        notifyDataSetChanged()
    }

    class WalletViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCurrency = itemView.findViewById<ImageView>(R.id.img_currency)
        val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        val tvAmount = itemView.findViewById<TextView>(R.id.tv_amount)
        val tvValue = itemView.findViewById<TextView>(R.id.tv_value)
    }
}