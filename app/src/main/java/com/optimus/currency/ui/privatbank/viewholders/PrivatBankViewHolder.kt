package com.optimus.currency.ui.privatbank.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.currency.data.model.PrivatBankCurrency
import com.optimus.currency.databinding.PrivatBankItemCellBinding
import com.optimus.currency.extensions.toDecimalFormat

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class PrivatBankViewHolder(private val binding: PrivatBankItemCellBinding, private val onItemClick: (String?) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, onItemClick: (String?) -> Unit): PrivatBankViewHolder {
            val binding = PrivatBankItemCellBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
            )
            return PrivatBankViewHolder(binding, onItemClick)
        }
    }

    fun bind(privatBankDataView: PrivatBankCurrency){
        binding.root.setOnClickListener { onItemClick.invoke(privatBankDataView.currency) }
        binding.tvCurrency.text = privatBankDataView.currency
        binding.purchaseRatePb.text = privatBankDataView.purchaseRate?.toDecimalFormat()
        binding.saleRatePb.text = privatBankDataView.saleRate?.toDecimalFormat()
    }
}