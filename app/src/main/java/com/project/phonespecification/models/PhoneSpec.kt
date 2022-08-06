package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneSpec(
    val data: List<PhoneDetail>,
    val status: Boolean
):Parcelable