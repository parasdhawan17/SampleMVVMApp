package com.example.dindinnassignment.ui.fakedata

import com.example.dindinnassignment.datamodels.OrderInfo
import com.example.dindinnassignment.ui.orders.repositories.BaseOrdersRepository

class FakeOrdersRepository(private val data : OrderInfo) : BaseOrdersRepository {
    override suspend fun fetchOrders(): OrderInfo {
       return data
    }
}