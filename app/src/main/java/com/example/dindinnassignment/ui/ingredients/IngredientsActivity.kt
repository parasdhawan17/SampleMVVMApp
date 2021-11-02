package com.example.dindinnassignment.ui.ingredients

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.dindinnassignment.ServiceLocator
import com.example.dindinnassignment.datamodels.CategoryInfo
import com.example.dindinnassignment.databinding.ActivityIngredientsBinding
import com.example.dindinnassignment.ui.ingredients.adapters.SectionsPagerAdapter
import com.example.dindinnassignment.ui.ingredients.repositories.IngredientsRepository

class IngredientsActivity : AppCompatActivity() {

    private val repository = IngredientsRepository(ServiceLocator.getApiService())

    val viewModel : IngredientsViewModel by viewModels { IngredientsViewModelFactory(repository) }

    private lateinit var binding: ActivityIngredientsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIngredientsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.categoryLiveData.observe(this, Observer {
            setTabs(it.data)
        })

        binding.etSearch.addTextChangedListener {
            viewModel.search(it.toString())
        }
    }

    private fun setTabs(list : ArrayList<CategoryInfo.Category>){
        val sectionsPagerAdapter = SectionsPagerAdapter(this,list, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }

}