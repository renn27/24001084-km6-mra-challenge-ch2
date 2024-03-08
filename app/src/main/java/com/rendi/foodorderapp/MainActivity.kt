package com.rendi.foodorderapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rendi.foodorderapp.adapter.CatalogAdapter
import com.rendi.foodorderapp.adapter.CategoryAdapter
import com.rendi.foodorderapp.databinding.ActivityMainBinding
import com.rendi.foodorderapp.model.Catalog
import com.rendi.foodorderapp.model.Category

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val categoryAdapter = CategoryAdapter()
    private val catalogAdapter = CatalogAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListCategory()
        setListCatalog()
    }

    private fun setListCategory() {
        val data = listOf(
            Category(image = R.drawable.ic_rice, name = "Nasi"),
            Category(image = R.drawable.ic_noodle, name = "Mie"),
            Category(image = R.drawable.ic_snack, name = "Snack"),
            Category(image = R.drawable.ic_drink, name = "Minuman")
        )
        binding.rvCategory.apply {
            adapter = this@MainActivity.categoryAdapter
        }
        categoryAdapter.submitData(data)
    }

    private fun setListCatalog() {
        val data = listOf(
            Catalog(image = R.drawable.img_ayam_bakar, price = 40000.0, name = "Ayam Bakar"),
            Catalog(image = R.drawable.img_ayam_goreng, price = 40000.0, name = "Ayam Goreng"),
            Catalog(image = R.drawable.img_ayam_geprek, price = 40000.0, name = "Ayam Geprek"),
            Catalog(image = R.drawable.img_sate_usus_ayam, price = 5000.0, name = "Sate Usus Ayam")
        )

        binding.rvCatalog.apply {
            adapter = this@MainActivity.catalogAdapter
        }
        catalogAdapter.submitData(data)
    }
}