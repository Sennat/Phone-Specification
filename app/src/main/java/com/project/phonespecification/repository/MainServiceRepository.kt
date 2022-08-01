package com.project.phonespecification.repository

import com.project.phonespecification.services.ServiceState
import kotlinx.coroutines.flow.Flow

interface MainServiceRepository {
    suspend fun retrievePhoneData(): Flow<ServiceState>
    suspend fun retrievePhoneBrand(details: String): Flow<ServiceState>
    suspend fun retrievePhoneDetails(details: String): Flow<ServiceState>
}