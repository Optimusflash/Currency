package com.optimus.currency.ui.nbu.viewholders

import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.optimus.currency.R
import com.optimus.currency.data.model.NBUCurrency
import com.optimus.currency.databinding.NbuBankItemCellBinding
import com.optimus.currency.extensions.toDecimalFormat

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class NBUViewHolder(private val binding: NbuBankItemCellBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val alphaCodeFormat = binding.root.resources.getString(R.string.value_alpha_code)
    private val currencyUAHFormat = binding.root.resources.getString(R.string.currency_UAH_format)
    private val root = binding.root

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
        binding.tvRateNbu.text = String.format(currencyUAHFormat, nbuCurrency.rate?.toDecimalFormat())
        binding.tvNbuAlphaName.text = String.format(alphaCodeFormat, nbuCurrency.alphaName)
        if (nbuCurrency.isSelected) {
            binding.root.setBackgroundResource(R.color.color_olive)
        }
        else {
            binding.root.setBackgroundResource(R.color.white)
        }
//
    }
}