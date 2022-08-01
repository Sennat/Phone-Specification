package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelResponse(
    val current_page: Int,
    val last_page: Int,
    val phones: List<PhoneInfo>,
    val title: String
):Parcelable