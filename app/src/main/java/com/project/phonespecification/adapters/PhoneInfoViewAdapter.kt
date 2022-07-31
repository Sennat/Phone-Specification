package com.project.phonespecification.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.phonespecification.databinding.PhoneListItemBinding
import com.project.phonespecification.models.PhoneInfo

class PhoneInfoViewAdapter(private val phoneInfoList: List<PhoneInfo>) : RecyclerView.Adapter<PhoneInfoViewAdapter.PhoneInfoViewHolder>() {

    inner class PhoneInfoViewHolder(private val binding: PhoneListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PhoneInfo) {
            binding.apply {
                txtBrand.text = item.brand
                txtModel.text = item.phone_name
                txtDesc.text = item.slug

                Glide.with(img)
                    .load(item.image)
                    .into(img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneInfoViewHolder {
        return PhoneInfoViewHolder(PhoneListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: PhoneInfoViewHolder, position: Int) {
        holder.onBind(phoneInfoList[position])
    }

    override fun getItemCount(): Int {
        return phoneInfoList.size
    }
}