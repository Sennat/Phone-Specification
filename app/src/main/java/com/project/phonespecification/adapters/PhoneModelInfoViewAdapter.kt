package com.project.phonespecification.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.phonespecification.R
import com.project.phonespecification.databinding.PhoneListItemBinding
import com.project.phonespecification.models.ModelInfo
import com.project.phonespecification.models.ModelsResponse
import com.project.phonespecification.models.PhonesInfo
import java.util.*

class PhoneModelInfoViewAdapter(
    private val phoneInfoList: MutableList<PhonesInfo> = mutableListOf(),
    private val openDetails: (modelType: String) -> Unit
) : RecyclerView.Adapter<PhoneModelInfoViewAdapter.PhoneInfoViewHolder>() {

    fun setPhoneInfoList(newList: List<PhonesInfo>) {
        phoneInfoList.clear()
        phoneInfoList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class PhoneInfoViewHolder(private val binding: PhoneListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PhonesInfo) {
            binding.apply {
                txtBrand.text = item.brand.uppercase()
                val re = Regex("[^A-Za-z0-9 ]")
                txtModel.text = re.replace(item.phoneName.uppercase(), " ")
                txtDesc.text = item.slug

                Glide.with(img)
                    .load(item.image)
                    .placeholder(R.drawable.ic_downloading)
                    .into(img)

                binding.root.setOnClickListener { openDetails(item.brand) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneInfoViewHolder {
        return PhoneInfoViewHolder(
            PhoneListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhoneInfoViewHolder, position: Int) {
        holder.onBind(phoneInfoList[position])
    }

    override fun getItemCount(): Int {
        return phoneInfoList.size
    }
}