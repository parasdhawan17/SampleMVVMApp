package com.example.dindinnassignment.ui.ingredients.repositories

import com.example.dindinnassignment.datamodels.IngredientsInfo

interface BaseCategoryRepository {
    suspend fun fetchIngredientsByCategory(id : Int) : IngredientsInfo
    suspend fun fetchIngredientsByCategoryAndName(id : Int,name : String) : IngredientsInfo
}