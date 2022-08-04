package com.project.phonespecification.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhonesInfo(
    val brand: String,
    @SerializedName("phone_name") val phoneName: String,
    val slug: String,
    val image: String,
    val detail: String,
): Parcelable
