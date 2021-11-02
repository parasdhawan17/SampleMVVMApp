package com.example.dindinnassignment.retrofit

import com.example.dindinnassignment.datamodels.CategoryInfo
import com.example.dindinnassignment.datamodels.IngredientsInfo
import com.example.dindinnassignment.datamodels.OrderInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(EndPoints.GET_ORDERS)
    suspend fun getOrders(
    ): OrderInfo

    @GET(EndPoints.GET_INGREDIENTS_BY_CATEGORY)
    suspend fun getIngredientsByCategory(
        @Query("category_id") id : Int,
    ): IngredientsInfo

    @GET(EndPoints.GET_ALL_CATEGORIES)
    suspend fun getAllCategories(
    ): CategoryInfo

    @GET(EndPoints.GET_INGREDIENTS_BY_CATEGORY_AND_NAME)
    suspend fun getIngredientsByCategoryAndName(
        @Query("category_id") categoryId : Int,
        @Query("name") name : String,
    ): IngredientsInfo
}