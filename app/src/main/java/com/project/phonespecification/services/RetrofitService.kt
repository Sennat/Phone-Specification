package com.project.phonespecification.services

import com.project.phonespecification.models.BrandsResponse
import com.project.phonespecification.models.NetworkResponse
import com.project.phonespecification.models.PhoneSpec
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("/v2/brands")
    suspend fun fetchPhoneData(): Response<BrandsResponse>

    @GET("/v2/brands/{brand_slug}")
    suspend fun fetchPhoneBrand(
        @Path("brand_slug") brand_slug: String
    ): Response<BrandsResponse>

    @GET("/v2/brands/{title}")
    suspend fun fetchPhoneDetail(
        @Path("title") title: String
    ): Response<PhoneSpec>
}