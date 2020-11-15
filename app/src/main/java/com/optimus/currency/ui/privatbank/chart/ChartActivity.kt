package com.optimus.currency.ui.privatbank.chart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.optimus.currency.databinding.ActivityChartBinding

class ChartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChartBinding

    companion object {
        fun newIntent(context: Context) = Intent(context, ChartActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "График валюты"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}
