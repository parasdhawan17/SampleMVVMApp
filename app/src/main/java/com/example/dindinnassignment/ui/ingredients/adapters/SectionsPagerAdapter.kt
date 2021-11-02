package com.example.dindinnassignment.ui.ingredients.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.dindinnassignment.datamodels.CategoryInfo
import com.example.dindinnassignment.ui.ingredients.PlaceholderFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, val list: List<CategoryInfo.Category>, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1, list[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].title
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return list.size
    }
}