package com.project.phonespecification.repository

import com.project.phonespecification.services.ServiceState
import kotlinx.coroutines.flow.Flow

interface MainServiceRepository {
    suspend fun getPhonesData(): Flow<ServiceState>
    suspend fun getPhoneBrand(details: String): Flow<ServiceState>
    suspend fun getPhoneDetails(details: String): Flow<ServiceState>
}