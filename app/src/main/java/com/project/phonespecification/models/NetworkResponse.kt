package com.project.phonespecification.models

data class NetworkResponse(
    val data: List<BrandsResponse>,
    val status: Boolean
)