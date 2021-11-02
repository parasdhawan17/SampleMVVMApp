package com.example.dindinnassignment.ui.ingredients

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dindinnassignment.datamodels.CategoryInfo
import com.example.dindinnassignment.getOrAwaitValue
import com.example.dindinnassignment.ui.fakedata.FakeIngredientsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class IngredientsViewModelTest  {

    private val fakeData = getFakeCategoryInfo()

    private val fakeIngredientsRepository = FakeIngredientsRepository(fakeData)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel : IngredientsViewModel

    @Before
    fun setUp() {
        viewModel = IngredientsViewModel(fakeIngredientsRepository)
    }

    @Test
    fun init_categoryFetchTest(){
        val dataSize = viewModel.categoryLiveData.getOrAwaitValue().data.size
        MatcherAssert.assertThat(dataSize, CoreMatchers.`is`(fakeData.data.size))
    }

    @Test
    fun search_QueryUpdateTest(){
        val searchQuery = "Chicken"
        viewModel.search(searchQuery)
        val query = viewModel.searchQuery.getOrAwaitValue()
        MatcherAssert.assertThat(query, CoreMatchers.`is`(searchQuery))
    }

    private fun getFakeCategoryInfo() : CategoryInfo{
        val category1 = CategoryInfo.Category(
            id = 1,
            title = "Bento"
        )
        val category2 = CategoryInfo.Category(
            id = 2,
            title = "Main"
        )
        val category3 = CategoryInfo.Category(
            id = 3,
            title = "Appetizer"
        )
        return CategoryInfo(arrayListOf(category1,category2,category3))
    }
}