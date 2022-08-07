package com.project.phonespecification.services

import com.project.phonespecification.repository.RetrofitServiceRepository
import com.project.phonespecification.services.network.RetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RetrofitServiceImplementation @Inject constructor(private val service: RetrofitService) : RetrofitServiceRepository {
    override suspend fun retrievePhoneData(): Flow<ServiceState> =
        flow {
            try {
                val res = service.fetchPhoneData()
                if (res.isSuccessful) {
                    emit(res.body()?.let {
                        ServiceState.Success(it)
                    } ?: throw Exception("ups ... No response data with status code of ${HttpError.BadRequest.code}"))
                } else {
                    throw Exception("Failed to fetch data!")
                }
            } catch (e: Exception) {
                emit(ServiceState.Error(e))
            }
        }

    override suspend fun retrievePhoneModel(model: String): Flow<ServiceState> =
        flow {
            try {
                val res = service.fetchPhoneBrand(model)
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

    override suspend fun retrievePhoneBrandDetail(detail: String): Flow<ServiceState> =
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

    override suspend fun getPhoneBrandByBrand(input: String): Flow<ServiceState> {
        TODO("Not yet implemented")
    }


}