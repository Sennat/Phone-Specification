package com.project.phonespecification.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.phonespecification.databinding.PhoneCardListBinding
import com.project.phonespecification.models.BrandsInfo

class PhoneCardViewAdapter(
    private val itemList: MutableList<BrandsInfo> = mutableListOf(),
    private val getSlugName: (String) -> Unit
) : RecyclerView.Adapter<PhoneCardViewAdapter.PhoneCardViewHolder>() {

    fun setPhoneBrandDataList(newList: List<BrandsInfo>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class PhoneCardViewHolder(private val binding: PhoneCardListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: BrandsInfo) {
            binding.txtBrand.text = item.brand_name.uppercase()
            val re = Regex("[^A-Za-z0-9 ]")
            binding.txtModel.text = re.replace(item.brand_slug.uppercase(), " ")
            //binding.txtModel.text = item.brand_slug.trim("_")

            //getSlugName(item.slug)
            binding.root.setOnClickListener {
                getSlugName(item.brand_slug)
            }
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