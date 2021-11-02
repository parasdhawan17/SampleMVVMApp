package com.example.dindinnassignment.ui.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dindinnassignment.ServiceLocator
import com.example.dindinnassignment.datamodels.CategoryInfo
import com.example.dindinnassignment.databinding.FragmentIngredientsBinding
import android.util.DisplayMetrics
import com.example.dindinnassignment.ui.ingredients.adapters.IngredientsDetailsAdapter

import com.example.dindinnassignment.ui.ingredients.repositories.CategoryRepository
import kotlinx.android.synthetic.main.fragment_ingredients.*
import kotlin.math.roundToInt


class PlaceholderFragment : Fragment() {

    private var _binding: FragmentIngredientsBinding? = null

    private val binding get() = _binding!!

    private var repository = CategoryRepository(ServiceLocator.getApiService())

    private val viewModel : PageViewModel by viewModels { PageViewModelFactory(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setCategoryId(arguments?.getInt(ARG_CATEGORY_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        val root = binding.root
        setListView()

        (requireActivity() as IngredientsActivity).viewModel.searchQuery.observe(viewLifecycleOwner, Observer {
            viewModel.search(it)
        })

        viewModel.ingredientInfo.observe(viewLifecycleOwner, Observer {
            val adapter = IngredientsDetailsAdapter(it.data)
            rvList.adapter = adapter
        })

        return root
    }

    private fun setListView(){
        val display = requireActivity().windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = resources.displayMetrics.density
        val dpWidth = outMetrics.widthPixels / density
        val columns = (dpWidth / 150).roundToInt()
        val mLayoutManager = GridLayoutManager(activity, columns)
        binding.rvList.layoutManager = mLayoutManager
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_CATEGORY_ID = "category_id"


        @JvmStatic
        fun newInstance(sectionNumber: Int,category: CategoryInfo.Category): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putInt(ARG_CATEGORY_ID, category.id)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}