package com.example.dindinnassignment

import com.example.dindinnassignment.retrofit.ApiService
import com.example.dindinnassignment.retrofit.RetrofitClient

object ServiceLocator {
    fun getApiService() : ApiService{
        return RetrofitClient.getRetrofitClient().create(ApiService::class.java)
    }
}