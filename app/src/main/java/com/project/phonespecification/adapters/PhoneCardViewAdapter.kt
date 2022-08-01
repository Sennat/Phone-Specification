package com.project.phonespecification.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.phonespecification.databinding.PhoneCardListBinding
import com.project.phonespecification.models.Brands
import com.project.phonespecification.models.BrandsResponse

class PhoneCardViewAdapter(private val itemList: MutableList<Brands> = mutableListOf()) :
    RecyclerView.Adapter<PhoneCardViewAdapter.PhoneCardViewHolder>() {

    fun setPhoneBrandDataList(newList: List<Brands>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class PhoneCardViewHolder(private val binding: PhoneCardListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Brands) {
            binding.txtBrand.text = item.brand_name.uppercase()
            binding.txtModel.text = item.brand_slug.substring(0).uppercase()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneCardViewHolder {
        return PhoneCardViewHolder(
            PhoneCardListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhoneCardViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}