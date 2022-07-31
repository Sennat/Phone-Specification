package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhoneInfo(
    val brand: String,
    val phone_name: String,
    val slug: String,
    val device_count: Int,
    val image: String,
    val detail: String
) : Parcelable