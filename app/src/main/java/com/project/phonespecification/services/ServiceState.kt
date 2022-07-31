package com.project.phonespecification.services

sealed class ServiceState {
    object Loading: ServiceState()
    class Error(val error: Exception): ServiceState()
    class Success<T>(val response: T): ServiceState()
}
