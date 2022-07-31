package com.project.phonespecification.services

import com.project.phonespecification.repository.MainServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataService(private val service: RetrofitService) : MainServiceRepository {
    override suspend fun getPhonesData(): Flow<ServiceState> =
        flow {
            try {
                val res = service.fetchPhoneData()
                if (res.isSuccessful) {
                    emit(res.body()?.let {
                        ServiceState.Success(it)
                    } ?: throw Exception("ups ... No response data!"))
                } else {
                    throw Exception("Failed to fetch data!")
                }
            } catch (e: Exception) {
                emit(ServiceState.Error(e))
            }
        }

    override suspend fun getPhoneBrand(brand: String): Flow<ServiceState> =
        flow {
            try {
                val res = service.fetchPhoneBrand(brand)
                if (res.isSuccessful) {
                    emit(res.body()?.let {
                        ServiceState.Success(it)
                    } ?: throw Exception("ups ... No response data!"))
                } else {
                    throw Exception("Failed to fetch data!")
                }
            } catch (e: Exception) {
                emit(ServiceState.Error(e))
            }
        }

    override suspend fun getPhoneDetails(detail: String): Flow<ServiceState> =
        flow {
            try {
                val res = service.fetchPhoneDetail(detail)
                if (res.isSuccessful) {
                    emit(res.body()?.let {
                        ServiceState.Success(it)
                    } ?: throw Exception("ups ... No response data!"))
                } else {
                    throw Exception("Failed to fetch data!")
                }
            } catch (e: Exception) {
                emit(ServiceState.Error(e))
            }
        }

}