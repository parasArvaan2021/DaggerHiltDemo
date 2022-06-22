package com.example.daggerhiltdemo.service

import com.example.daggerhiltdemo.response.MainReponse
import retrofit2.Call
import javax.inject.Inject

class ApiClient @Inject constructor(private val service: ApiService) {

    suspend fun getExpenseLists(
    ) : MainReponse{
       return service.getExpenseLists()
    }
}