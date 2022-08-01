package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneInfo(
    val brand: String,
    val detail: String,
    val image: String,
    val phone_name: String,
    val slug: String
):Parcelable