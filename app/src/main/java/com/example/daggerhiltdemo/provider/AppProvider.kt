package com.example.daggerhiltdemo.provider

import com.example.daggerhiltdemo.repository.MainRepository
import com.example.daggerhiltdemo.service.ApiClient
import com.example.daggerhiltdemo.service.ApiService
import com.example.daggerhiltdemo.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppProvider {

    @Singleton
    @Provides
    fun providesMainRepository(client: ApiClient): MainRepository = MainRepository(client)

    @Singleton
    @Provides
    fun providesApiClient(service: ApiService): ApiClient = ApiClient(service)

    @Provides
    fun providesBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Singleton
    fun providesApiService(okHttpClient: OkHttpClient, baseUrl: String): ApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}