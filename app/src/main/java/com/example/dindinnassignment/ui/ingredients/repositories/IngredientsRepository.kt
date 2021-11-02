package com.example.dindinnassignment.ui.ingredients.repositories

import com.example.dindinnassignment.retrofit.ApiService
import com.example.dindinnassignment.ui.ingredients.repositories.BaseIngredientsRepository

class IngredientsRepository(private val apiService: ApiService) : BaseIngredientsRepository {
    override suspend fun fetchCategories() = apiService.getAllCategories()
}