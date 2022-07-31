package com.project.phonespecification.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.phonespecification.databinding.PhoneCardDetailItemsBinding

class PhoneDetailFragment : BaseFragment() {

    private lateinit var binding: PhoneCardDetailItemsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = PhoneCardDetailItemsBinding.inflate(layoutInflater)



        return binding.root
    }
}