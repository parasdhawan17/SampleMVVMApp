package com.example.dindinnassignment.ui.fakedata

import com.example.dindinnassignment.datamodels.CategoryInfo
import com.example.dindinnassignment.ui.ingredients.repositories.BaseIngredientsRepository

class FakeIngredientsRepository(private val data : CategoryInfo) : BaseIngredientsRepository {
    override suspend fun fetchCategories(): CategoryInfo {
        return data
    }
}