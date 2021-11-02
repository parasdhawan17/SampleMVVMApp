package com.example.dindinnassignment.ui.ingredients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dindinnassignment.datamodels.CategoryInfo
import com.example.dindinnassignment.ui.ingredients.repositories.BaseIngredientsRepository
import kotlinx.coroutines.launch

class IngredientsViewModel(private val repository: BaseIngredientsRepository) : ViewModel() {
    private val _categoryLiveData = MutableLiveData<CategoryInfo>()
    val categoryLiveData = _categoryLiveData

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery = _searchQuery

    init {
        viewModelScope.launch {
            _categoryLiveData.value = repository.fetchCategories()
        }
    }

    fun search(text : String){
        _searchQuery.value =text
    }
}

class IngredientsViewModelFactory(
    private val repository: BaseIngredientsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IngredientsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IngredientsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}