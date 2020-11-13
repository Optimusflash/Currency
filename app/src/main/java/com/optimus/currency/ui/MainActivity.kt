package com.optimus.currency.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.optimus.currency.R
import com.optimus.currency.di.Injector
import com.optimus.currency.di.ViewModelFactory
import com.optimus.currency.ui.privatbank.viewmodel.PrivatBankViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PrivatBankViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDaggerComponent()
        initViewModel()
        initViews()
        setObservers()

    }

    private fun initDaggerComponent() {
        Injector.getAppComponent().inject(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PrivatBankViewModel::class.java)
    }

    private fun initViews() {

    }

    private fun setObservers() {
        viewModel.currencies.observe(this, Observer {
            val log = it.map { cur ->
                cur.currency
            }
            Log.e("M_MainActivity", log.toString())
        })
    }
}