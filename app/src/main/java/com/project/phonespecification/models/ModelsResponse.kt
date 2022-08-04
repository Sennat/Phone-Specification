package com.project.phonespecification.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelsResponse(
    val status: Boolean,
    val data: ModelInfo
):Parcelable