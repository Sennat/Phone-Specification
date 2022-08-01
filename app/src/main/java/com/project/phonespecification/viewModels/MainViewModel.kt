package com.project.phonespecification.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.phonespecification.repository.MainServiceRepository
import com.project.phonespecification.services.ServiceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

const val TAG = "STATE CHECK"

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainServiceRepository, private val dispatcher: CoroutineDispatcher) : ViewModel() {
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

    fun getPhoneData() {
        viewModelSafeScope.launch(dispatcher) {
            // collect from our flow
            repository.retrievePhoneData().collect { state ->
                // postValue updates LiveData asynchronously
                _listData.postValue(state)
            }
        }
    }

    fun getPhoneDetail(details: String) {
        viewModelSafeScope.launch(dispatcher) {
            // collect from flow
            repository.retrievePhoneDetails(details).collect { state ->
                // postValue updates LiveData asynchronously
                _listData.postValue(state)
            }
        }
    }

    // setValue is not asynchronous
    fun setLoading() {_listData.value = ServiceState.Loading}

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
