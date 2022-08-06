package com.project.phonespecification.repository

import com.project.phonespecification.services.ServiceState
import kotlinx.coroutines.flow.Flow

interface MainServiceRepository {
    suspend fun retrievePhoneData(): Flow<ServiceState>
    suspend fun retrievePhoneModel(model: String): Flow<ServiceState>
    suspend fun retrievePhoneBrandDetail(detail: String): Flow<ServiceState>
    suspend fun getPhoneBrandByBrand(input: String): Flow<ServiceState>
}