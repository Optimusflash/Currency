package com.optimus.currency.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.optimus.currency.R
import com.optimus.currency.databinding.ActivityMainBinding
import com.optimus.currency.ui.nbu.fragments.NBUFragment
import com.optimus.currency.ui.privatbank.chart.ChartActivity
import com.optimus.currency.ui.privatbank.fragments.PrivatBankFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: SharedViewModel by viewModels()
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        if(savedInstanceState==null){
            attachFragments()
        }
        setObservers()

    }

    private fun attachFragments() {
        val privatBankFragment = PrivatBankFragment()
        val nbuFragment = NBUFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.pb_container, privatBankFragment)
            .replace(R.id.nbu_container,nbuFragment)
            .commit()
    }

    private fun setObservers() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.graph) {
            val intent = ChartActivity.newIntent(this)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}