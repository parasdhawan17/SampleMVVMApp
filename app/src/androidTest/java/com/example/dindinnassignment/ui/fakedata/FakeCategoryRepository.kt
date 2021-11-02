package com.example.dindinnassignment.ui.fakedata

import com.example.dindinnassignment.datamodels.IngredientsInfo
import com.example.dindinnassignment.ui.ingredients.repositories.BaseCategoryRepository

class FakeCategoryRepository(private val categoryData: ArrayList<IngredientsInfo>,
                             private val searchCategoryData: ArrayList<IngredientsInfo>) :
    BaseCategoryRepository {
    override suspend fun fetchIngredientsByCategory(id: Int): IngredientsInfo {
        return categoryData[id-1]
    }

    override suspend fun fetchIngredientsByCategoryAndName(id: Int, name: String): IngredientsInfo {
        return searchCategoryData[id-1]
    }
}