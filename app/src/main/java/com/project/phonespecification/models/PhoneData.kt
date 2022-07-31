package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhoneData (
    val brand: String,
    val phone_name: String,
    val thumbnail: String,
    val release_date: String,
    val dimension: String,
    val os: String,
    val storage: String
) : Parcelable
