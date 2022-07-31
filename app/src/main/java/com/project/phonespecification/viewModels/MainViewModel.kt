package com.project.phonespecification.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.phonespecification.repository.MainServiceRepository
import com.project.phonespecification.services.ServiceState
import kotlinx.coroutines.*


const val TAG = "STATE CHECK"

class MainViewModel( private val repository: MainServiceRepository, private val dispatcher: CoroutineDispatcher) : ViewModel() {
    private val _listData = MutableLiveData<ServiceState>()
    val listData: LiveData<ServiceState> get() = _listData
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    var job: Job? = null

    private val exceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            onError("Exception handled: ${throwable.localizedMessage}")
            Log.e(TAG, "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}",throwable)
        }
    }

    private val viewModelSafeScope by lazy {
        viewModelScope + exceptionHandler
    }

    fun getWeatherForecast(details: String) {
        viewModelSafeScope.launch(dispatcher) {
            // collect from our flow
            repository.getPhoneDetails(details).collect { state ->
                // postValue updates LiveData asynchronously
                _listData.postValue(state)
            }
        }
    }

    // setValue is not asynchronous
    fun setLoading() {_listData.value = ServiceState.Loading}
    fun getSuccessMessage() { println("Success")}

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
