package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailResponse(
    val data: PhoneDetail,
    val status: Boolean
) : Parcelable
