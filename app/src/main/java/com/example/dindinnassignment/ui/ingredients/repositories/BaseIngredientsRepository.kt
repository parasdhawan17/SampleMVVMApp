package com.example.dindinnassignment.ui.ingredients.repositories

import com.example.dindinnassignment.datamodels.CategoryInfo

interface BaseIngredientsRepository  {
    suspend fun fetchCategories() : CategoryInfo
}