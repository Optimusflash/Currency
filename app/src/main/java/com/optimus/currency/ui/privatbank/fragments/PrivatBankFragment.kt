package com.optimus.currency.ui.privatbank.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.optimus.currency.databinding.FragmentPrivatBankBinding
import com.optimus.currency.di.Injector
import com.optimus.currency.di.ViewModelFactory
import com.optimus.currency.ui.DatePickerFragment
import com.optimus.currency.ui.SharedViewModel
import com.optimus.currency.ui.privatbank.adapter.PrivatBankAdapter
import com.optimus.currency.ui.privatbank.viewmodel.PrivateBankViewModel
import javax.inject.Inject


class PrivatBankFragment : Fragment(), DatePickerFragment.OnDateSetListener {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: PrivateBankViewModel
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var fragmentPrivatBankBinding: FragmentPrivatBankBinding
    private val pbAdapter by lazy {
        PrivatBankAdapter ( sharedViewModel::handleCurrencyClick )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentPrivatBankBinding = FragmentPrivatBankBinding.inflate(inflater, container, false)
        return fragmentPrivatBankBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDaggerComponent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initViews()
        setObservers()
    }

    private fun initDaggerComponent() {
        Injector.getAppComponent().inject(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PrivateBankViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(SharedViewModel::class.java)
    }

    private fun initViews() {

        fragmentPrivatBankBinding.pbRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        fragmentPrivatBankBinding.pbRecyclerview.adapter = pbAdapter
        val datePickerFragment = DatePickerFragment.newInstance(this)

        fragmentPrivatBankBinding.tvPbDatePicker.setOnClickListener {
            datePickerFragment.show(childFragmentManager, "PB picker") //TODO
        }

    }

    private fun setObservers() {
        viewModel.currencies.observe(viewLifecycleOwner, {
            pbAdapter.updateData(it)
        })
        viewModel.calendarDate.observe(viewLifecycleOwner, {
            fragmentPrivatBankBinding.tvPbDatePicker.text = it
        })
    }

    override fun onDateSet(dateInMillis: Long) {
        viewModel.handleDate(dateInMillis)
    }

}