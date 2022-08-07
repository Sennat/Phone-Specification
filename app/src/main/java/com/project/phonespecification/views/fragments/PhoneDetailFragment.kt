package com.project.phonespecification.views.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.project.phonespecification.R
import com.project.phonespecification.databinding.FragmentPhoneDetailBinding
import com.project.phonespecification.models.DetailResponse
import com.project.phonespecification.services.ServiceState

class PhoneDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentPhoneDetailBinding
    private val args: PhoneDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhoneDetailBinding.inflate(layoutInflater)
        liveDataObserver()
        return binding.root
    }

    private fun liveDataObserver() {
        mainViewModelFragment.modelInfo
            .observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    is ServiceState.Loading -> {
                        mainViewModelFragment.getPhoneModelDetail(args.modelInfo)
                    }
                    is ServiceState.Error -> {
                        binding.apply {
                            loading.visibility = View.GONE
                            txtLoading.text = uiState.error.message
                        }
                    }
                    is ServiceState.Success<*> -> {
                        val res = uiState.response as DetailResponse
                        binding.apply {
                            loading.visibility = View.GONE
                            txtLoading.visibility = View.GONE
                            txtBrand.text = res.data.brand.uppercase()
                            txtModel.text = res.data.phone_name
                            txtDimension.text = "Dimension: ${res.data.dimension.uppercase().apply { Color.BLACK }}"
                            txtOs.text = "OS: ${res.data.os.uppercase().apply { Color.BLACK }}"
                            txtStorage.text = "Storage: ${res.data.storage.uppercase().apply { Color.BLACK }}"
                            txtReleaseDate.text = res.data.release_date.uppercase().apply { Color.BLACK }

                            Glide.with(thumbnail)
                                .load(res.data.thumbnail)
                                .placeholder(R.drawable.ic_downloading)
                                .into(thumbnail)
                        }
                    }
                }
            }
    }
}