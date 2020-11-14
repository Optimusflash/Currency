package com.optimus.currency.ui.privatbank.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.currency.data.model.PrivatBankCurrency
import com.optimus.currency.ui.privatbank.viewholders.PrivatBankViewHolder

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class PrivatBankAdapter(private val onItemClick: (String?) -> Unit): RecyclerView.Adapter<PrivatBankViewHolder>() {
    private val currencyItems: MutableList<PrivatBankCurrency> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PrivatBankViewHolder.create(parent,onItemClick)

    override fun onBindViewHolder(holder: PrivatBankViewHolder, position: Int) {
        holder.bind(currencyItems[position])
    }

    override fun getItemCount() = currencyItems.size

    fun updateData(items: List<PrivatBankCurrency>){
        currencyItems.clear()
        currencyItems.addAll(items)
        notifyDataSetChanged()
    }

}