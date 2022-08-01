package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Brands(
    val brand_id: Int,
    val brand_name: String,
    val brand_slug: String,
    val detail: String,
    val device_count: Int
):Parcelable
