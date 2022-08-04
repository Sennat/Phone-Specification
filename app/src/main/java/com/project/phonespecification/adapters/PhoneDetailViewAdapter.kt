package com.project.phonespecification.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.phonespecification.databinding.PhoneCardDetailItemsBinding
import com.project.phonespecification.models.PhoneDetails

class PhoneDetailViewAdapter(
    private val phoneDataListItem: MutableList<PhoneDetails> = mutableListOf(),
    private val openDetails: (PhoneDetails) -> Unit
) : RecyclerView.Adapter<PhoneDetailViewAdapter.DetailViewHolder>() {

    fun setPhoneList(newList: List<PhoneDetails>) {
        phoneDataListItem.clear()
        phoneDataListItem.addAll(newList)
        notifyDataSetChanged()
    }

    inner class DetailViewHolder(private val binding: PhoneCardDetailItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PhoneDetails) {
            binding.apply {
                txtBrand.text = item.brand
                txtModel.text = item.phone_name
                txtDimension.text = item.dimension
                txtOs.text = item.release_date
                txtStorage.text = item.storage
                txtReleaseDate.text = item.release_date

                Glide.with(thumbnail)
                    .load(item.thumbnail)
                    .into(thumbnail)

                binding.root.setOnClickListener {
                    openDetails(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            PhoneCardDetailItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.onBind(phoneDataListItem[position])
    }

    override fun getItemCount(): Int = phoneDataListItem.size

}