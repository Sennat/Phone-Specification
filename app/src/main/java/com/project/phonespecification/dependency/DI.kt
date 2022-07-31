package com.project.phonespecification.dependency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.project.phonespecification.repository.MainServiceRepository
import com.project.phonespecification.services.DataService
import com.project.phonespecification.services.RetrofitService
import com.project.phonespecification.utils.BASE_URL
import com.project.phonespecification.viewModels.MainViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object DI {

    private val service = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitService::class.java)

    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRepository() = DataService(service)
    private fun provideDispatcher() = Dispatchers.IO

    fun provideViewModel(storeOwner: ViewModelStoreOwner): MainViewModel {
        return ViewModelProvider(storeOwner, object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(provideRepository(), provideDispatcher()) as T
            }
        })[MainViewModel::class.java]
    }
}