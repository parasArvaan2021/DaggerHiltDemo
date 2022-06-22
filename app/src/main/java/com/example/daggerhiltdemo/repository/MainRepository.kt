package com.example.daggerhiltdemo.repository

import androidx.lifecycle.MutableLiveData
import com.example.daggerhiltdemo.response.MainReponse
import com.example.daggerhiltdemo.service.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(private val client: ApiClient) {

    suspend fun getExpenseList(): MainReponse {
        return client.getExpenseLists()
    }
}