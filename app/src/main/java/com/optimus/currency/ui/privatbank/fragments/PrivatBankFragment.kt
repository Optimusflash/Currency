package com.optimus.currency.ui.privatbank.fragments


import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.optimus.currency.databinding.FragmentPrivatBankBinding
import com.optimus.currency.di.Injector
import com.optimus.currency.di.ViewModelFactory
import com.optimus.currency.ui.privatbank.adapter.PrivatBankAdapter
import com.optimus.currency.ui.SharedViewModel
import com.optimus.currency.ui.privatbank.viewmodel.PrivateBankViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class PrivatBankFragment : Fragment() {
    private lateinit var fragmentPrivatBankBinding: FragmentPrivatBankBinding
    private lateinit var calendar: Calendar
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: PrivateBankViewModel
    private val pbAdapter by lazy {
        PrivatBankAdapter()
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
        viewModel = ViewModelProvider(
           this,
            viewModelFactory
        ).get(PrivateBankViewModel::class.java)
    }

    private fun initViews() {
        fragmentPrivatBankBinding.pbRecyclerview.layoutManager =
            LinearLayoutManager(requireContext())
        fragmentPrivatBankBinding.pbRecyclerview.adapter = pbAdapter

        calendar = Calendar.getInstance(Locale.getDefault())
        updateLabel()

        val date = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            updateLabel()
        }
        val dialog = DatePickerDialog(
            requireContext(),
            date,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        fragmentPrivatBankBinding.tvPbDatePicker.setOnClickListener {
            dialog.show()
        }
    }

    private fun setObservers() {
        viewModel.currencies.observe(viewLifecycleOwner, {
            pbAdapter.updateData(it)
        })
    }

    private fun updateLabel() {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        fragmentPrivatBankBinding.tvPbDatePicker.text = dateFormat.format(calendar.time)
    }


}