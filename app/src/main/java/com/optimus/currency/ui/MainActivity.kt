package com.optimus.currency.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.optimus.currency.R
import com.optimus.currency.databinding.ActivityMainBinding
import com.optimus.currency.di.Injector
import com.optimus.currency.di.ViewModelFactory
import com.optimus.currency.ui.nbu.fragments.NBUFragment
import com.optimus.currency.ui.privatbank.fragments.PrivatBankFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SharedViewModel
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initDaggerComponent()
        initViewModel()
        if(savedInstanceState==null){
            attachFragments()
        }
        setObservers()

    }

    private fun initDaggerComponent() {
        Injector.getAppComponent().inject(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SharedViewModel::class.java)
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
        if (item.itemId == R.id.graph){
            Toast.makeText(this, "menu clicked", Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}