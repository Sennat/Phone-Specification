package com.project.phonespecification.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Specification(
    val specs: List<Spec>,
    val title: String
):Parcelable