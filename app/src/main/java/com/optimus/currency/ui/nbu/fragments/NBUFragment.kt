package com.optimus.currency.ui.nbu.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.optimus.currency.R
import com.optimus.currency.databinding.FragmentNbuBinding
import com.optimus.currency.di.Injector
import com.optimus.currency.di.ViewModelFactory
import com.optimus.currency.ui.nbu.adapter.NBUAdapter
import com.optimus.currency.ui.nbu.viewmodel.NBUViewModel
import javax.inject.Inject


class NBUFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: NBUViewModel
    private lateinit var fragmentNbuBinding: FragmentNbuBinding

    private val nbuAdapter by lazy { NBUAdapter() }

    companion object {
        fun newInstance(param1: String, param2: String) =       //TODO impl later
            NBUFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

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
    }

    private fun initViews() {
        fragmentNbuBinding.nbuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        fragmentNbuBinding.nbuRecyclerView.adapter = nbuAdapter
    }

    private fun setObservers() {
        viewModel.currenciesNBU.observe(viewLifecycleOwner, {
            nbuAdapter.updateData(it)
        })
    }
}