package com.example.dindinnassignment.ui.ingredients

import androidx.lifecycle.*
import com.example.dindinnassignment.datamodels.IngredientsInfo
import com.example.dindinnassignment.ui.ingredients.repositories.BaseCategoryRepository
import kotlinx.coroutines.launch

class PageViewModel( private val repository: BaseCategoryRepository) : ViewModel() {


    private val _ingredientInfo  = MutableLiveData<IngredientsInfo>()
    val ingredientInfo = _ingredientInfo

    private var id : Int?=null

    fun setCategoryId(id: Int?) {
        this.id = id
        id?.let {
            viewModelScope.launch {
                _ingredientInfo.postValue(repository.fetchIngredientsByCategory(id))
            }
        }
    }

    fun search(name : String){
        viewModelScope.launch {
            _ingredientInfo.postValue(repository.fetchIngredientsByCategoryAndName(id!!,name))
        }
    }
}

class PageViewModelFactory(
    private val repository: BaseCategoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}