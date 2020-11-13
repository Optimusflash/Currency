package com.optimus.currency.ui.nbu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.optimus.currency.R
import com.optimus.currency.databinding.FragmentNbuBinding


class NBUFragment : Fragment() {
    private lateinit var fragmentNbuBinding: FragmentNbuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentNbuBinding = FragmentNbuBinding.inflate(inflater, container, false)
        return fragmentNbuBinding.root
    }

    companion object {
            fun newInstance(param1: String, param2: String) =       //TODO impl later
            NBUFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}