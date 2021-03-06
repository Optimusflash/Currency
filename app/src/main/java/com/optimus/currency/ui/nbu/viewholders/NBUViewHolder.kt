package com.optimus.currency.ui.nbu.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
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

    fun bind(nbuCurrency: NBUCurrency, position: Int){
        val defaultColor = if (position % 2 == 0) R.color.white else R.color.color_green_light
        binding.tvNbuCurrency.text = nbuCurrency.currencyUkrName
        binding.tvRateNbu.text = String.format(currencyUAHFormat, nbuCurrency.rate?.toDecimalFormat())
        binding.tvNbuAlphaName.text = String.format(alphaCodeFormat, nbuCurrency.alphaName)
        binding.root.setBackgroundResource(if (nbuCurrency.isSelected) R.color.color_olive else defaultColor)
    }
}