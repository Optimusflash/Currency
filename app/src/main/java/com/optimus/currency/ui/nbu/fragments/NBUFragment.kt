package com.optimus.currency.ui.nbu.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.optimus.currency.databinding.FragmentNbuBinding
import com.optimus.currency.di.Injector
import com.optimus.currency.di.ViewModelFactory
import com.optimus.currency.extensions.formatDate
import com.optimus.currency.ui.DatePickerFragment
import com.optimus.currency.ui.SharedViewModel
import com.optimus.currency.ui.nbu.adapter.NBUAdapter
import com.optimus.currency.ui.nbu.viewmodel.NBUViewModel
import javax.inject.Inject


class NBUFragment : Fragment(),DatePickerFragment.OnDateSetListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: NBUViewModel
    private lateinit var fragmentNbuBinding: FragmentNbuBinding
    private lateinit var sharedViewModel: SharedViewModel

    private val nbuAdapter by lazy { NBUAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDaggerComponent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentNbuBinding = FragmentNbuBinding.inflate(inflater, container, false)
        return fragmentNbuBinding.root
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
        viewModel = ViewModelProvider(this, viewModelFactory).get(NBUViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(SharedViewModel::class.java)
    }

    private fun initViews() {
        fragmentNbuBinding.nbuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        fragmentNbuBinding.nbuRecyclerView.adapter = nbuAdapter
        val datePickerFragment = DatePickerFragment.newInstance(this)
        fragmentNbuBinding.tvNbuDatePicker.setOnClickListener {
            datePickerFragment.show(childFragmentManager, "NBU picker")  //TODO
        }
    }

    private fun setObservers() {
//        viewModel.currenciesNBU.observe(viewLifecycleOwner, {
//            nbuAdapter.updateData(it)
//        })

        viewModel.ldNbuItems.observe(viewLifecycleOwner, {
            nbuAdapter.updateData(it)
        })

        viewModel.calendarDate.observe(viewLifecycleOwner, {
            fragmentNbuBinding.tvNbuDatePicker.text = it.formatDate()
        })

        sharedViewModel.clickedCurrencyCode.observe(viewLifecycleOwner, {
            Log.e("M_NBUFragment", "clickedCurrencyCode $it")
            viewModel.handleClick(it)
        })

        viewModel.currencyPositionIndex.observe(viewLifecycleOwner, {
            Log.e("M_NBUFragment", "$it")
            if (it == null || it == -1)  return@observe
            fragmentNbuBinding.nbuRecyclerView.smoothScrollToPosition(it)
        })
    }

    override fun onDateSet(dateInMillis: Long) {
        viewModel.handleDate(dateInMillis)
    }
}