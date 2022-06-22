package com.example.daggerhiltdemo.service

import com.example.daggerhiltdemo.response.MainReponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getExpenseLists(): MainReponse
}