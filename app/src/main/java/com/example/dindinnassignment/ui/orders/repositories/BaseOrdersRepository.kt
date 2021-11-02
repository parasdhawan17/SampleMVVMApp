package com.example.dindinnassignment.ui.orders.repositories

import com.example.dindinnassignment.datamodels.OrderInfo

interface BaseOrdersRepository {
    suspend fun fetchOrders() : OrderInfo
}