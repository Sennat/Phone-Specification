package com.project.phonespecification.services

import com.project.phonespecification.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("v2/brands")
    suspend fun fetchPhoneData(): Response<BrandsResponse>

    @GET("v2/brands/{brand}") //brand_slug
    suspend fun fetchPhoneBrand(
        @Path("brand") brand_slug: String
    ): Response<ModelsResponse>

    @GET("v2/brands/{title}")
    suspend fun fetchPhoneDetail(
        @Path("title") title: String
    ): Response<PhoneSpec>

    @GET("v2/brands/{search}")
    suspend fun getPhoneByBrand(
        @Query("search") brand_slug: String
    ): Response<BrandsResponse>
}