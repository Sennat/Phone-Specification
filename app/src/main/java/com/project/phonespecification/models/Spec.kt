package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Spec(
    val key: String,
    val `val`: List<String>
):Parcelable