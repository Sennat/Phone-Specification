package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrandsResponse(
    val status: Boolean,
    val data: List<BrandsInfo>
):Parcelable