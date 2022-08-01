package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneDetails(
    val brand: String,
    val dimension: String,
    val os: String,
    val phone_images: List<String>,
    val phone_name: String,
    val release_date: String,
    val specifications: List<Specification>,
    val storage: String,
    val thumbnail: String
): Parcelable