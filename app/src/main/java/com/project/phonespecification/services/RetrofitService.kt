package com.project.phonespecification.services

import com.project.phonespecification.models.BrandResponse
import com.project.phonespecification.models.NetworkResponse
import com.project.phonespecification.models.PhoneResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("/v2/brands")
    suspend fun fetchPhoneData(): Response<NetworkResponse>

    @GET("/v2/brands/")
    suspend fun fetchPhoneBrand(
        @Query("brand") brand: String
    ): Response<BrandResponse>

    @GET("/v2/brands/")
    suspend fun fetchPhoneDetail(
        @Query("detail") detail: String
    ): Response<PhoneResponse>
}