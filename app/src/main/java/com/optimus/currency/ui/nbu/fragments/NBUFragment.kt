package com.optimus.currency.ui.nbu.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.optimus.currency.databinding.FragmentNbuBinding
import com.optimus.currency.extensions.formatDate
import com.optimus.currency.ui.privatbank.dialog.DatePickerFragment
import com.optimus.currency.ui.main.SharedViewModel
import com.optimus.currency.ui.nbu.adapter.NBUAdapter
import com.optimus.currency.ui.nbu.viewmodel.NBUViewModel
import com.optimus.currency.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NBUFragment : Fragment(), DatePickerFragment.OnDateSetListener {

    private val viewModel: NBUViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentNbuBinding

    private val nbuAdapter by lazy { NBUAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNbuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setObservers()
    }

    private fun initViews() {
        binding.nbuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.nbuRecyclerView.adapter = nbuAdapter

        val datePickerFragment = DatePickerFragment.newInstance(this)

        binding.tvNbuDatePicker.setOnClickListener {
            datePickerFragment.show(childFragmentManager, datePickerFragment.tag)
        }
    }

    private fun setObservers() {
        viewModel.currenciesNBU.observe(viewLifecycleOwner, { resource->
            when (resource){
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.nbuRecyclerView.visibility = View.VISIBLE
                    resource.data?.let {
                        if (it.isEmpty()) showToast("Empty data. Choose another date")
                        nbuAdapter.updateData(it)
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.nbuRecyclerView.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.nbuRecyclerView.visibility = View.GONE
                    showToast(resource.message)
                }
            }
        })

        viewModel.calendarDate.observe(viewLifecycleOwner, {
            binding.tvNbuDatePicker.text = it.formatDate()
        })

        sharedViewModel.clickedCurrencyCode.observe(viewLifecycleOwner, {
            Log.e("M_NBUFragment", "clickedCurrencyCode $it")
            viewModel.handleClick(it)
        })

        viewModel.currencyPositionIndex.observe(viewLifecycleOwner, {
            Log.e("M_NBUFragment", "$it")
            if (it == null || it == -1)  return@observe
            binding.nbuRecyclerView.smoothScrollToPosition(it)
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