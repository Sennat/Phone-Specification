package com.project.phonespecification.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.project.phonespecification.adapters.PhoneModelViewAdapter
import com.project.phonespecification.databinding.FragmentPhoneListItemBinding
import com.project.phonespecification.models.*
import com.project.phonespecification.services.ServiceState

class PhoneListItemFragment : BaseFragment() {
    private lateinit var binding: FragmentPhoneListItemBinding
    private val phoneInfoViewAdapter by lazy { PhoneModelViewAdapter(openDetails = ::openDetails) }
    private val args: PhoneListItemFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhoneListItemBinding.inflate(layoutInflater)
        liveDataObserver()

        return binding.root
    }

    private fun liveDataObserver() {
        mainViewModelFragment.modelData
            .observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    is ServiceState.Loading -> {
                        mainViewModelFragment.getPhoneModel(args.modelType)
                    }
                    is ServiceState.Error -> {
                        binding.pbLoading.visibility = View.GONE
                    }
                    is ServiceState.Success<*> -> {
                        binding.apply {
                            pbLoading.visibility = View.GONE
                            phoneInfoViewAdapter.setPhoneInfoList((uiState.response as ModelsResponse).data.phones)
                            recyclerviewPhoneListItem.adapter = phoneInfoViewAdapter
                            binding.recyclerviewPhoneListItem.apply {
                                set3DItem(true)
                                setAlpha(true)
                                setInfinite(true)
                            }
                        }
                    }
                }
            }
    }

    private fun openDetails(modelType: String) {
        mainViewModelFragment.setModelDetailLoading()
        findNavController().navigate(PhoneListItemFragmentDirections.actionPhoneListItemFragmentToPhoneDetailFragment(modelType))
    }
}