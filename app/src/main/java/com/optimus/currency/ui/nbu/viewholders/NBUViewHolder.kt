package com.optimus.currency.ui.nbu.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.currency.data.model.NBUCurrency
import com.optimus.currency.data.model.PrivatBankCurrency
import com.optimus.currency.databinding.NbuBankItemCellBinding
import com.optimus.currency.databinding.PrivatBankItemCellBinding

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class NBUViewHolder(private val binding: NbuBankItemCellBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): NBUViewHolder {
            val binding = NbuBankItemCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return NBUViewHolder(binding)
        }
    }

    fun bind(nbuCurrency: NBUCurrency){
        binding.tvNbuCurrency.text = nbuCurrency.currencyUkrName
        binding.tvRateNbu.text = nbuCurrency.rate.toString()
        binding.tvNbuAlphaName.text = nbuCurrency.alphaName.toString()
    }
}