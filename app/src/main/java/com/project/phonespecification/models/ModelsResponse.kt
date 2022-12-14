package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelsResponse(
    val status: Boolean,
    val data: ModelInfo
):Parcelable