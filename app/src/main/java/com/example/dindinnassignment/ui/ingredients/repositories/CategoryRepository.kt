package com.example.dindinnassignment.ui.ingredients.repositories

import com.example.dindinnassignment.retrofit.ApiService

class CategoryRepository(private val apiService: ApiService) : BaseCategoryRepository {
    override suspend fun fetchIngredientsByCategory(id : Int) = apiService.getIngredientsByCategory(id)
    override suspend fun fetchIngredientsByCategoryAndName(id : Int,name : String) = apiService.getIngredientsByCategoryAndName(id,name)
}
