package com.project.phonespecification.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.phonespecification.databinding.PhoneCardListBinding
import com.project.phonespecification.models.PhoneData

class PhoneCardViewAdapter(private val itemList: List<PhoneData>): RecyclerView.Adapter<PhoneCardViewAdapter.PhoneViewHolder>() {

    inner class PhoneViewHolder(private val binding: PhoneCardListBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PhoneData) {
            binding.txtBrand.text = item.brand
            binding.txtModel .text = item.phone_name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        return PhoneViewHolder(PhoneCardListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
       holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}