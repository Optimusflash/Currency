package com.optimus.currency.ui.privatbank.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.optimus.currency.databinding.FragmentPrivatBankBinding
import com.optimus.currency.ui.privatbank.adapter.PrivatBankAdapter
import com.optimus.currency.ui.privatbank.dialog.DatePickerFragment
import com.optimus.currency.ui.main.SharedViewModel
import com.optimus.currency.ui.privatbank.viewmodel.PrivateBankViewModel
import com.optimus.currency.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PrivatBankFragment : Fragment(), DatePickerFragment.OnDateSetListener {

    private val viewModel: PrivateBankViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentPrivatBankBinding
    private val pbAdapter by lazy {
        PrivatBankAdapter ( sharedViewModel::handleCurrencyClick )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentPrivatBankBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setObservers()
    }


    private fun initViews() {

        binding.pbRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.pbRecyclerview.adapter = pbAdapter
        val datePickerFragment = DatePickerFragment.newInstance(this)

        binding.tvPbDatePicker.setOnClickListener {
            datePickerFragment.show(childFragmentManager, datePickerFragment.tag)
        }

    }

    private fun setObservers() {
        viewModel.currencies.observe(viewLifecycleOwner, { resources ->
            when(resources){
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.pbRecyclerview.visibility = View.VISIBLE
                    resources.data?.let {
                        if (it.isEmpty()) showToast("Empty data. Choose another date")
                        pbAdapter.updateData(it)
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.pbRecyclerview.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.pbRecyclerview.visibility = View.GONE
                    showToast(resources.message)
                }
            }
        })

        viewModel.calendarDate.observe(viewLifecycleOwner, {
            binding.tvPbDatePicker.text = it
        })
    }

    override fun onDateSet(dateInMillis: Long) {
        viewModel.handleDate(dateInMillis)
    }

    private fun showToast(message: String?) {
        message ?: return
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}