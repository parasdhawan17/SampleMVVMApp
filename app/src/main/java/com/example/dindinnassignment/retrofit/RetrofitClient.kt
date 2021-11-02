package com.example.dindinnassignment.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getRetrofitClient() : Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val mockData = MockInterceptor()
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(mockData)
                .build()
            return Retrofit.Builder()
                .baseUrl(EndPoints.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}