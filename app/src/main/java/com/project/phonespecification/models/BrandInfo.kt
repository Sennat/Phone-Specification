package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BrandInfo(
    val brand_id: Int,
    val brand_name: String,
    val brand_slug: String,
    val device_count: String,
    val detail: String
) : Parcelable