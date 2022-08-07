package com.project.phonespecification.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.project.phonespecification.adapters.PhoneCardViewAdapter
import com.project.phonespecification.databinding.FragmentPhoneCardBinding
import com.project.phonespecification.models.BrandsResponse
import com.project.phonespecification.services.ServiceState

class PhoneCardFragment : BaseFragment() {
    private lateinit var binding: FragmentPhoneCardBinding
    private val phoneCardViewAdapter by lazy { PhoneCardViewAdapter(getSlugName = ::getSlugName) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPhoneCardBinding.inflate(layoutInflater)

        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        mainViewModelFragment.listData
            .observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    is ServiceState.Loading -> {
                        mainViewModelFragment.getPhoneData()
                    }
                    is ServiceState.Error -> {
                        binding.pbLoading.visibility = View.GONE
                        binding.tvLoadingText.text = uiState.error.message
                    }
                    is ServiceState.Success<*> -> {
                        binding.apply {
                            pbLoading.visibility = View.GONE
                            tvLoadingText.visibility = View.GONE
                            phoneCardViewAdapter.setPhoneBrandDataList((uiState.response as BrandsResponse).data)
                            recyclerviewPhoneCard.adapter = phoneCardViewAdapter
                        }
                    }
                }
            }
    }

    // get slug from recyclerview
    // use slug in next fragment to make api call
    private fun getSlugName(slug: String) {
        mainViewModelFragment.setModelLoading()
        findNavController().navigate(PhoneCardFragmentDirections.actionPhoneCardFragmentToPhoneListItemFragment(slug))
    }

    override fun onStop() {
        super.onStop()
        if (authListener != null) {
            auth.removeAuthStateListener(authListener)
        }
    }
}