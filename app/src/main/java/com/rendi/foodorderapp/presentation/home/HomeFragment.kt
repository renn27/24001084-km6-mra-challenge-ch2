package com.rendi.foodorderapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.rendi.foodorderapp.R
import com.rendi.foodorderapp.data.datasource.CatalogDataSource
import com.rendi.foodorderapp.data.datasource.CatalogDataSourceImpl
import com.rendi.foodorderapp.data.datasource.CategoryDataSource
import com.rendi.foodorderapp.data.datasource.CategoryDataSourceImpl
import com.rendi.foodorderapp.databinding.FragmentHomeBinding
import com.rendi.foodorderapp.model.Catalog
import com.rendi.foodorderapp.presentation.detail.DetailActivity
import com.rendi.foodorderapp.presentation.home.adapter.CatalogAdapter
import com.rendi.foodorderapp.presentation.home.adapter.CategoryAdapter
import com.rendi.foodorderapp.presentation.home.adapter.OnItemClickedListener

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var categoryAdapter: CategoryAdapter? = null
    private val categoryDataSource: CategoryDataSource by lazy {
        CategoryDataSourceImpl()
    }
    private var catalogAdapter: CatalogAdapter? = null
    private val catalogDataSource: CatalogDataSource by lazy {
        CatalogDataSourceImpl()
    }
    private var isUsingListMode: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindCategoryItem()
        bindCatalogList(isUsingListMode)
        setClickAction()
    }

    private fun bindCategoryItem() {
        categoryAdapter = CategoryAdapter()
        binding.rvCategoryItem.apply {
            adapter = this@HomeFragment.categoryAdapter
        }
        categoryAdapter?.submitData(categoryDataSource.getCategoryItem())
    }

    private fun setClickAction() {
        binding.ivChangeListMode.setOnClickListener {
            isUsingListMode = !isUsingListMode
            setIcon(isUsingListMode)
            bindCatalogList(isUsingListMode)
        }
    }

    private fun setIcon(usingListMode: Boolean) {
        binding.ivChangeListMode.setImageResource(if (usingListMode) R.drawable.ic_list else R.drawable.ic_grid)
    }

    private fun bindCatalogList(isUsingListMode: Boolean) {
        val listMode = if (isUsingListMode) CatalogAdapter.MODE_LIST else CatalogAdapter.MODE_GRID
        catalogAdapter = CatalogAdapter(
            listMode = listMode,
            listener = object : OnItemClickedListener<Catalog> {
                override fun onItemClicked(item: Catalog) {
                    navigateToDetail(item)
                }
            }
        )
        binding.rvCatalogList.apply {
            adapter = this@HomeFragment.catalogAdapter
            layoutManager = GridLayoutManager(requireContext(), if (isUsingListMode) 1 else 2)
        }
        catalogAdapter?.submitData(catalogDataSource.getCatalogFood())
    }

    private fun navigateToDetail(item: Catalog) {
        DetailActivity.startActivity(requireContext(), item)
    }
}