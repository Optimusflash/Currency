package com.optimus.currency.ui.nbu.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.currency.data.model.NBUCurrency
import com.optimus.currency.ui.nbu.viewholders.NBUViewHolder

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class NBUAdapter : RecyclerView.Adapter<NBUViewHolder>() {
    private val currencyItems: MutableList<NBUCurrency> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NBUViewHolder.create(parent)

    override fun onBindViewHolder(holder: NBUViewHolder, position: Int) {
            holder.bind(currencyItems[position], position)
    }

    override fun getItemCount() = currencyItems.size

    fun updateData(items: List<NBUCurrency>) {
        currencyItems.clear()
        currencyItems.addAll(items)
        notifyDataSetChanged()
    }

}