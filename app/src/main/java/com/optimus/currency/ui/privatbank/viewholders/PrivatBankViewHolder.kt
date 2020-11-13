package com.optimus.currency.ui.privatbank.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.currency.data.model.PrivatBankCurrency
import com.optimus.currency.databinding.PrivatBankItemCellBinding

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class PrivatBankViewHolder(private val binding: PrivatBankItemCellBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): PrivatBankViewHolder {
            val binding = PrivatBankItemCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return PrivatBankViewHolder(binding)
        }
    }

    fun bind(privatBankDataView: PrivatBankCurrency){
        binding.tvCurrency.text = privatBankDataView.currency
        binding.purchaseRatePb.text = privatBankDataView.purchaseRate.toString()
        binding.saleRatePb.text = privatBankDataView.saleRate.toString()
    }
}