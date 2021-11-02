package com.example.dindinnassignment.ui.orders.repositories

import com.example.dindinnassignment.retrofit.ApiService

class OrdersRepository(private val apiService: ApiService) : BaseOrdersRepository {
    override suspend fun fetchOrders() = apiService.getOrders()
}