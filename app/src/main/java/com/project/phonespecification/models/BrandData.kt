package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BrandData(
    val title: String,
    val current_page: Int,
    val phones: List<PhoneInfo>
) : Parcelable