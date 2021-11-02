package com.example.dindinnassignment.ui.ingredients

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dindinnassignment.datamodels.IngredientsInfo
import com.example.dindinnassignment.getOrAwaitValue
import com.example.dindinnassignment.ui.fakedata.FakeCategoryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PageViewModelTest {

    private lateinit var fakeCategoryData : ArrayList<IngredientsInfo>

    private lateinit var fakeSearchData : ArrayList<IngredientsInfo>

    private lateinit var fakeCategoryRepository : FakeCategoryRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel : PageViewModel

    @Before
    fun setup(){

        fakeCategoryData = getFakeIngredientData()

        fakeSearchData = getFakeSearchIngredientData()

        fakeCategoryRepository = FakeCategoryRepository(fakeCategoryData,fakeSearchData)

        viewModel = PageViewModel(fakeCategoryRepository)
    }

    @Test
    fun setCategoryId_populateDataTest(){
        viewModel.setCategoryId(fakeCategoryData[0].data[0].categoryId)
        val categoryId = viewModel.ingredientInfo.getOrAwaitValue().data[0].categoryId
        MatcherAssert.assertThat(categoryId, CoreMatchers.`is`(fakeCategoryData[0].data[0].categoryId))
    }

    @Test
    fun search_dataTest(){
        viewModel.setCategoryId(fakeCategoryData[0].data[0].categoryId)
        viewModel.search("hello")
        val data = viewModel.ingredientInfo.getOrAwaitValue()
        MatcherAssert.assertThat(data, CoreMatchers.`is`(fakeSearchData[0]))
    }

    private fun getFakeIngredientData() : ArrayList<IngredientsInfo>{
        val ingredient = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Olives",
            stock = 2,
            imageURL = "www.image.com"
        )
        val ingredient1 = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Peanut butter",
            stock = 3,
            imageURL = "www.image.com"
        )
        val ingredient2 = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Canned tomatoes",
            stock = 4,
            imageURL = "www.image.com"
        )
        val ingredient3 = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Salsa",
            stock = 2,
            imageURL = "www.image.com"
        )
        val ingredient4 = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Tuna fish",
            stock = 9,
            imageURL = "www.image.com"
        )
        val category1 = IngredientsInfo(arrayListOf(ingredient,ingredient1,ingredient2,ingredient3,ingredient4))

        val c2ingredient = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Mayonnaise",
            stock = 2,
            imageURL = "www.image.com"
        )
        val c2ingredient1 = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Dijon mustard",
            stock = 3,
            imageURL = "www.image.com"
        )
        val c2ingredient2 = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Soy sauce",
            stock = 4,
            imageURL = "www.image.com"
        )
        val category2 = IngredientsInfo(arrayListOf(c2ingredient,c2ingredient1,c2ingredient2))

        val c3ingredient = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Chili paste",
            stock = 2,
            imageURL = "www.image.com"
        )
        val c3ingredient1 = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Hot sauce",
            stock = 3,
            imageURL = "www.image.com"
        )
        val c3ingredient2 = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Ketchup",
            stock = 4,
            imageURL = "www.image.com"
        )
        val category3 = IngredientsInfo(arrayListOf(c3ingredient,c3ingredient1,c3ingredient2))

        return arrayListOf(category1,category2,category3)
    }

    private fun getFakeSearchIngredientData() : ArrayList<IngredientsInfo>{
        val ingredient = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Olives",
            stock = 2,
            imageURL = "www.image.com"
        )
        val ingredient2 = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Canned tomatoes",
            stock = 4,
            imageURL = "www.image.com"
        )
        val ingredient4 = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Tuna fish",
            stock = 9,
            imageURL = "www.image.com"
        )
        val category1 = IngredientsInfo(arrayListOf(ingredient,ingredient2,ingredient4))

        val c2ingredient = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Mayonnaise",
            stock = 2,
            imageURL = "www.image.com"
        )
        val c2ingredient2 = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Soy sauce",
            stock = 4,
            imageURL = "www.image.com"
        )
        val category2 = IngredientsInfo(arrayListOf(c2ingredient,c2ingredient2))

        val c3ingredient = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Chili paste",
            stock = 2,
            imageURL = "www.image.com"
        )
        val c3ingredient2 = IngredientsInfo.Ingredient(
            categoryId = 1,
            name = "Ketchup",
            stock = 4,
            imageURL = "www.image.com"
        )
        val category3 = IngredientsInfo(arrayListOf(c3ingredient,c3ingredient2))

        return arrayListOf(category1,category2,category3)
    }
}